package org.example.apispring.dto.response;

import lombok.Builder;
import lombok.Data;
import org.example.apispring.model.Category;
import org.example.apispring.model.Store;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private List<String> imageUrl = new ArrayList<>();
    private Category category;
    private String storeId;
    private int stock;
    private double rating;
    private int sold = 0;
}
