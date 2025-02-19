package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.request.CartItemRequest;
import org.example.apispring.dto.response.CartResponse;
import org.example.apispring.model.CartItem;
import org.example.apispring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public ApiResponse<CartResponse> getCart(@RequestParam String userId) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.getCartByUserId(userId))
                .build();
    }

    @PostMapping("/addToCart")
    public ApiResponse<CartResponse> addProductToCart(@RequestParam String userId, @RequestBody CartItemRequest cartItemRequest) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.addProductToCart(userId, cartItemRequest))
                .build();
    }
}
