package org.example.apispring.controller;

import org.example.apispring.dto.request.OrderCreationReq;
import org.example.apispring.dto.response.OrderItemResponse;
import org.example.apispring.model.Order;
import org.example.apispring.model.OrderItem;
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

    @GetMapping("/byStore/{storeId}")
    public List<OrderItemResponse> getByStoreId(@PathVariable String storeId) {
        return orderService.findByStoreId(storeId);
    }

    @PutMapping("/{orderId}/{status}")
    public Order updateOrderStatus(@PathVariable String orderId, @PathVariable String status) {
        return orderService.updateOrderStatus(orderId,status);
    }

    @PutMapping("/item/{itemId}/{status}")
    public OrderItemResponse changeItemStatus(@PathVariable String itemId, @PathVariable String status) {
        return orderService.changeItemStatus(itemId, status);
    }
}

