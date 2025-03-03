package org.example.apispring.service;

import org.example.apispring.model.Order;
import org.example.apispring.repository.OrderItemRepo;
import org.example.apispring.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    public List<Order> findAll() {
        return orderRepo.findAll();
    }

    public Order save(Order order) {
        return orderRepo.save(order);
    }
}
