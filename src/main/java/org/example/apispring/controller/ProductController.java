package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.request.ProductCreationReq;
import org.example.apispring.dto.request.ProductUpdateReq;
import org.example.apispring.dto.response.ProductDetailResponse;
import org.example.apispring.dto.response.ProductResponse;
import org.example.apispring.model.Product;
import org.example.apispring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shop/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ApiResponse<ProductResponse> create(@ModelAttribute ProductCreationReq product) throws IOException {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.createProduct(product))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<ProductResponse>> getProducts( @RequestParam(required = false) String storeId,
                                                           @RequestParam(required = false) String categoryId,
                                                           @RequestParam(required = false) Double minPrice,
                                                           @RequestParam(required = false) Double maxPrice,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
       return ApiResponse.<Page<ProductResponse>>builder()
               .data(productService.getProducts(storeId, categoryId, minPrice, maxPrice, page, size))
               .build();
    }

    @GetMapping("/get/{id}")
    public ApiResponse<ProductDetailResponse> getProduct(@PathVariable String id) {
        return ApiResponse.<ProductDetailResponse>builder()
                .data(productService.getProductDetail(id))
                .build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<ProductResponse> update(@ModelAttribute ProductUpdateReq product, @PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .data(productService.updateProduct(product))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> search(@RequestParam String name) {
        return ApiResponse.<List<ProductResponse>>builder()
                .data(productService.searchProducts(name))
                .build();
    }
}
