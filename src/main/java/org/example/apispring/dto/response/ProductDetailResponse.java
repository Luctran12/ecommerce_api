package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductDetailResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private List<String> imageUrl = new ArrayList<>();
    private Category category;
    private StoreResponse store;
    private int stock;
    private double rating;
}
