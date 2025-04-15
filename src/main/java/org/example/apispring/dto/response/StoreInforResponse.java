package org.example.apispring.dto.response;

import lombok.Data;
import org.example.apispring.model.Product;

import java.util.List;

@Data
public class StoreInforResponse {
    private int totalProducts;
    private int totalOrders;
    private double receipts;
    private List<OrderItemResponse> orderItems;
    private List<Product> products;
}
