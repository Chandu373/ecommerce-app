package com.chandu.order.contoller;

import com.chandu.order.record.OrderRequest;
import com.chandu.order.record.OrderResponse;
import com.chandu.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<Integer> create(@RequestBody @Valid OrderRequest request){
        return  ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/orders")
    public  ResponseEntity<List<OrderResponse>>  findAll(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(orderService.findOrderById(orderId));

    }
}
