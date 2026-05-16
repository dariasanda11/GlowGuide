package com.glowguide.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Order>> getOrdersByClient(@PathVariable String email) {
        return ResponseEntity.ok(orderRepository.findByClientEmailOrderByIdDesc(email));
    }
}