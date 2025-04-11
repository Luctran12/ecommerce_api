package org.example.apispring.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private ProductResponse product;
    private int quantity;
}
