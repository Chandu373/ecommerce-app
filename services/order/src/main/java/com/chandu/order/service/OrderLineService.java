package com.chandu.order.service;

import com.chandu.order.domain.OrderLine;
import com.chandu.order.mapper.OrderLineMapper;
import com.chandu.order.record.OrderLineRequest;
import com.chandu.order.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
