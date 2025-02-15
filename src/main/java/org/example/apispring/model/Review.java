package org.example.apispring.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Product product;

    private int rating; // Số sao (1 - 5)
    private String comment;
    private Date createdAt;
}

