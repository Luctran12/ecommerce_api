package org.example.apispring.controller;

import com.cloudinary.Api;
import org.example.apispring.dto.ApiResponse;
import org.example.apispring.dto.request.AddressUpdateReq;
import org.example.apispring.dto.request.SellerCreationReq;
import org.example.apispring.dto.request.UserCreationReq;
import org.example.apispring.dto.request.UserLoginReq;
import org.example.apispring.dto.response.JwtResponse;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.dto.response.UserResponse;
import org.example.apispring.model.Store;
import org.example.apispring.model.User;
import org.example.apispring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shop/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> create(@RequestBody UserCreationReq user) {
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

    @GetMapping("/findById")
    public ApiResponse<UserResponse> findById(@RequestParam String id) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.findById(id))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<JwtResponse> login(@RequestBody UserLoginReq userLoginReq) {
        return ApiResponse.<JwtResponse>builder()
                .data(userService.login(userLoginReq))
                .build();
    }

    @PostMapping("/address")
    public ResponseEntity<?> updateAddress(@RequestBody AddressUpdateReq addressUpdateReq) {
        userService.addressUpdate(addressUpdateReq);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/seller/regis")
    public ApiResponse<StoreResponse> sellerRegis(@RequestBody SellerCreationReq sellerCreationReq) {
        return ApiResponse.<StoreResponse>builder()
                .data(userService.sellerRegis(sellerCreationReq))
                .build();
    }

    @PostMapping("/seller/login")
    public ApiResponse<JwtResponse> sellerLogin(@RequestBody UserLoginReq userLoginReq) {
        return ApiResponse.<JwtResponse>builder()
                .data(userService.sellerLogin(userLoginReq))
                .build();
    }
}
