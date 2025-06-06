package org.example.apispring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "stores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    @Id
    private String id;
    private String name;
    private String description;
    private String address;
    private int districtId;
    private int wardCode;

    @DBRef
    private User owner; // Chủ cửa hàng

    @DBRef(lazy = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>(); // Danh sách sản phẩm của cửa hàng
}

