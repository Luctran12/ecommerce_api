package org.example.apispring.dto.request;

import org.example.apispring.model.OrderItem;

import java.util.List;

public class OrderCreationReq {
    private String userId;
    private List<OrderItem> items;
    private double totalPrice;
}
