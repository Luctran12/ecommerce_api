package org.example.apispring.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Data
public class ProductUpdateReq {
    private String name;          // Tên sản phẩm
    private String description;   // Mô tả sản phẩm
    private List<MultipartFile> image;
    private double price;         // Giá sản phẩm
    private int stock;         // Số lượng trong kho
}
