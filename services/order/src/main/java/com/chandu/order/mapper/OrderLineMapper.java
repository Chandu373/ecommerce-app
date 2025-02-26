package com.chandu.order.mapper;

import com.chandu.order.domain.Order;
import com.chandu.order.domain.OrderLine;
import com.chandu.order.record.OrderLineRequest;
import com.chandu.order.record.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return  OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                        .id(request.orderId())
                        .build())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return  new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
