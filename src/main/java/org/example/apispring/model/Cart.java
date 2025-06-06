package org.example.apispring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private List<CartItem> items = new ArrayList<>();
}
