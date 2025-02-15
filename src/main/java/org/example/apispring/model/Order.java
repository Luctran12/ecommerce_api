package org.example.apispring.model;

import lombok.*;
import org.example.apispring.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private List<OrderItem> items;

    private double totalPrice;
    private OrderStatus status; // "Pending", "Processing", "Shipped", "Delivered"
    private Date orderDate;
}

