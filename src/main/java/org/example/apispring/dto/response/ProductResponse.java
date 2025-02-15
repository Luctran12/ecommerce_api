package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.Category;
import org.example.apispring.model.Store;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private List<String> imageUrl;
    private Category category;
    private Store store;
    private int stock;
    private double rating;
}
