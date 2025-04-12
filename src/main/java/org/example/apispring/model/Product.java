package org.example.apispring.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int height;
    private int weight;
    private int length;
    private int width;
    @Nullable
    private List<String> imageUrl = new ArrayList<>();

    @DBRef
    private Category category;

    @DBRef
    private Store store; // Cửa hàng sở hữu sản phẩm

    private int stock; // Số lượng tồn kho
    @Nullable
    private double rating; // Trung bình đánh giá

    private int sold = 0;
}
