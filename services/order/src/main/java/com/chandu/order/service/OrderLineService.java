package com.chandu.order.service;

import com.chandu.order.domain.OrderLine;
import com.chandu.order.mapper.OrderLineMapper;
import com.chandu.order.record.OrderLineRequest;
import com.chandu.order.record.OrderLineResponse;
import com.chandu.order.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private OrderLineMapper orderLineMapper;

    public OrderLine saveOrderLine(OrderLineRequest request) {
        OrderLine orderLine = orderLineMapper.toOrderLine(request);
        return  orderLineRepository.save(orderLine);
    }

    public List<OrderLineResponse> findOrderLineByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLineMapper ::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
