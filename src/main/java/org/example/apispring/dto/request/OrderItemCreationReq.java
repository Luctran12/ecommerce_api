package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemCreationReq {
    private String productId;
    private int quantity;
    private double price;
}
