package com.chandu.order.mapper;

import com.chandu.order.domain.Order;
import com.chandu.order.record.OrderRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(@Valid OrderRequest request) {
        if(request == null){
            return  null;
        }
       return Order.builder()
               .id(request.id())
               .reference(request.reference())
               .totalAmount(request.amount())
               .paymentMethod(request.paymentMethod())
               .customerId(request.customerId())
               .build();
    }
}
