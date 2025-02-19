package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductCreationReq {
    private String name;          // Tên sản phẩm
    private String description;   // Mô tả sản phẩm
    private double price;         // Giá sản phẩm
    private int stock;         // Số lượng trong kho
    private String storeId;       // ID của cửa hàng đăng bán sản phẩm
    private List<MultipartFile> images = new ArrayList<>();

    // Cho phép chọn category có sẵn hoặc tạo mới
    private String categoryId;    // ID của category (nếu chọn từ danh sách có sẵn)
    private String categoryName;  // Tên category (nếu muốn tạo mới)
}
