package org.example.apispring.dto.response;

import lombok.Data;
import org.example.apispring.enums.OrderStatus;

import java.util.Date;

@Data
public class OrderItemResponse {
    private String id;
    private ProductResponse product;
    private int quantity;
    private OrderStatus status; // "Pending", "Processing", "Shipped", "Delivered"
    private String address;
    private Date date;
}
