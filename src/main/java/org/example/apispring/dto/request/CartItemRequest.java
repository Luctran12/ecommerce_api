package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.Product;

@Data
@Builder
public class CartItemRequest {
    private String productId;

    private int quantity;
}
