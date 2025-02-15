package org.example.apispring.controller;

import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.response.UserResponse;
import org.example.apispring.model.User;
import org.example.apispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> create(@RequestBody User user) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.save(user))
                .build();
    }

    @GetMapping("/findAll")
    public ApiResponse<List<UserResponse>> findAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .data(userService.findAll())
                .build();
    }

    @GetMapping("/fingById")
    public ApiResponse<UserResponse> findById(@RequestParam String id) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.findById(id))
                .build();
    }
}
