package org.example.apispring.dto.response;

import lombok.Data;
import org.example.apispring.enums.OrderStatus;

@Data
public class OrderItemResponse {
    private String id;
    private ProductResponse product;
    private int quantity;
    private OrderStatus status; // "Pending", "Processing", "Shipped", "Delivered"
}
