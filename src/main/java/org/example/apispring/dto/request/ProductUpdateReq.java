package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductUpdateReq {
    private String id;
    private String name;          // Tên sản phẩm
    private String description;   // Mô tả sản phẩm
    private double price;         // Giá sản phẩm
    private int stock;         // Số lượng trong kho
}
