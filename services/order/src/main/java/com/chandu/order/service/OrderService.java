package com.chandu.order.service;

import com.chandu.order.customer.CustomerClient;
import com.chandu.order.exception.BusinessException;
import com.chandu.order.exception.OrderNotFoundException;
import com.chandu.order.kafka.OrderProducer;
import com.chandu.order.mapper.OrderMapper;
import com.chandu.order.product.ProductClient;
import com.chandu.order.record.*;
import com.chandu.order.repository.OrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private OrderProducer orderProducer;
    private CustomerClient customerClient;
    private ProductClient productClient;

    public Integer createOrder(@Valid OrderRequest request) {
        //check customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(()->
                        new BusinessException(String.format("Cannot create Order: no customer exists with provided ID: %s", request.customerId())));

        // purchase product using product-service (RestTemplate)
        List<PurchaseResponse> purchaseProducts = productClient.purchaseProducts(request.products());
        // persist order
        var order = orderRepository.save(orderMapper.toOrder(request));
        // persist order-line
        saveOrderLines(order.getId(),request.products());

        // TODO start payment process

        // sent order confirmation notification broker(kafka)
        sendOrderConfirmation(customer,purchaseProducts,request);

        return order.getId();
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


    private void sendOrderConfirmation(CustomerResponse customer, List<PurchaseResponse> purchaseProducts, @Valid OrderRequest request) {
        orderProducer.sendOderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new OrderNotFoundException(
                        String.format("Order not Found with provided ID: %s"+orderId)));
    }
}
