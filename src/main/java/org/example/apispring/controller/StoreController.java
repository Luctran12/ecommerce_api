package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.request.StoreCreationReq;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ApiResponse<StoreResponse> createStore(@RequestBody StoreCreationReq req) {
        return ApiResponse.<StoreResponse>builder().data(storeService.createStore(req)).build();
    }
}
