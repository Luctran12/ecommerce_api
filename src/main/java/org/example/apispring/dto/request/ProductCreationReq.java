package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreationReq {
    private String name;          // Tên sản phẩm
    private String description;   // Mô tả sản phẩm
    private double price;         // Giá sản phẩm
    private int stock;         // Số lượng trong kho
    private String storeId;       // ID của cửa hàng đăng bán sản phẩm

    // Cho phép chọn category có sẵn hoặc tạo mới
    private String categoryId;    // ID của category (nếu chọn từ danh sách có sẵn)
    private String categoryName;  // Tên category (nếu muốn tạo mới)
}
