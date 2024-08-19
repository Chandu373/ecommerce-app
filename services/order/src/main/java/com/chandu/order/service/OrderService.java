package com.chandu.order.service;

import com.chandu.order.customer.CustomerClient;
import com.chandu.order.exception.BusinessException;
import com.chandu.order.mapper.OrderMapper;
import com.chandu.order.product.ProductClient;
import com.chandu.order.record.OrderLineRequest;
import com.chandu.order.record.OrderRequest;
import com.chandu.order.record.PurchaseRequest;
import com.chandu.order.repository.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineService orderLineService;
    private CustomerClient customerClient;
    private ProductClient productClient;

    public Integer createOrder(@Valid OrderRequest request) {
        //check customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->
                        new BusinessException(String.format("Cannot create Order: no customer exists with provided ID: %s", request.customerId())));

        // purchase product using product-service (RestTemplate)
        productClient.purchaseProducts(request.products());
        // persist order
        var order = orderRepository.save(orderMapper.toOrder(request));
        // persist order-line
        saveOrderLines(order.getId(),request.products());

        // TODO start payment process
        // sent order confirmation notification broker(kafka)
        return 0;
    }

    private void saveOrderLines(Integer orderId, @NotEmpty(message = "you should at least purchase one product") List<PurchaseRequest> products) {
      for(PurchaseRequest purchaseRequest :products){
        orderLineService.saveOrderLine(
                new OrderLineRequest(
                        null,
                        orderId,
                        purchaseRequest.productId(),
                        purchaseRequest.quantity()
                )
        );
       }
    }
}
