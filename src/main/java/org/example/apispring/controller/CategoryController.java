package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.model.Category;
import org.example.apispring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ApiResponse<List<Category>> getAllCategories() {
        return ApiResponse.<List<Category>>builder()
                .data(categoryService.findAll())
                .build();
    }
}
