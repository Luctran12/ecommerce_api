package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.request.ProductCreationReq;
import org.example.apispring.dto.request.StoreCreationReq;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/shop/store")
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ApiResponse<StoreResponse> createStore(@RequestBody StoreCreationReq req) {
        return ApiResponse.<StoreResponse>builder().data(storeService.createStore(req)).build();
    }

    @PostMapping("/addProductToStore")
    public ApiResponse<StoreResponse> addProductToStore(@RequestParam String storeId, @ModelAttribute ProductCreationReq req) throws IOException {
        return ApiResponse.<StoreResponse>builder()
                .data(storeService.addProductToStore(storeId, req))
                .build();
    }

    @DeleteMapping("/deleteProductFromStore")
    public ApiResponse<StoreResponse> deleteProductFromStore(@RequestParam String storeId, @RequestParam String productId) {
        return ApiResponse.<StoreResponse>builder()
                .data(storeService.deleteProductFromStore(storeId, productId))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<StoreResponse> getStoreById(@PathVariable String id) {
        return ApiResponse.<StoreResponse>builder().data(storeService.getStoreById(id)).build();
    }


}
