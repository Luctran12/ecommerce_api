package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.OrderItem;

import java.util.List;

@Data
@Builder
public class OrderCreationReq {
    private String userId;
    private List<OrderItemCreationReq> items;
    private String address;
    private double totalPrice;
}
