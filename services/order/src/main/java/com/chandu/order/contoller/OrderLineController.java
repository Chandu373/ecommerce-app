package com.chandu.order.contoller;

import com.chandu.order.record.OrderLineResponse;
import com.chandu.order.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findOrderLineByOrder(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderLineService.findOrderLineByOrderId(orderId));
    }
}
