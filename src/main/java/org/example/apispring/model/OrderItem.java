package org.example.apispring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order_items")
public class OrderItem {
    @Id
    private String id;

    @DBRef
    private Product product;

    private int quantity;
    private double price;
}

