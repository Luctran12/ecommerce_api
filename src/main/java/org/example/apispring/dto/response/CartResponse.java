package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.CartItem;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CartResponse {
    private List<CartItem> items = new ArrayList<>();
    private Double totalPrice = 0.0;
}
