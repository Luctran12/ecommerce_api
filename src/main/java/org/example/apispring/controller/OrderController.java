package org.example.apispring.controller;

import org.example.apispring.dto.request.OrderCreationReq;
import org.example.apispring.model.Order;
import org.example.apispring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/shop/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public Order createOrder(@RequestBody OrderCreationReq order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public List<Order> getOrderByUserId(@PathVariable String id) {
        return orderService.findByUserId(id);
    }
}
