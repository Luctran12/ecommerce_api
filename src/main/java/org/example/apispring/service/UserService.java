package org.example.apispring.service;

import org.example.apispring.config.JwtUtils;
import org.example.apispring.dto.request.*;
import org.example.apispring.dto.response.JwtResponse;
import org.example.apispring.dto.response.StoreResponse;
import org.example.apispring.dto.response.UserResponse;
import org.example.apispring.enums.Role;
import org.example.apispring.mapper.UserMapper;
import org.example.apispring.model.Store;
import org.example.apispring.model.User;
import org.example.apispring.repository.StoreRepo;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StoreService storeSerivce;

    @Autowired
    private UserMapper userMapper;

    public List<UserResponse> findAll() {
        List<User> users = userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            UserResponse userResponse = userMapper.toUserResponse(user);
            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public UserResponse save(UserCreationReq userReq) {
        User user = userMapper.toUser(userReq);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepo.findByAccountName(userReq.getAccountName()) != null) throw new RuntimeException("User already exists");

        user.setRole(Role.User);
        return userMapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse findById(String id) {
        return  userMapper.toUserResponse(userRepo.findById(id).get());
    }

    public JwtResponse login(UserLoginReq userLoginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginReq.getAccountName(), userLoginReq.getPassword())
            );
            String id = userRepo.findByAccountName(userLoginReq.getAccountName()).getId();
            String token = jwtUtils.generateJwtToken(authentication, id);
            return new JwtResponse(token);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Sai tên đăng nhập hoặc mật khẩu");
        }
    }

    public void addressUpdate(AddressUpdateReq addressUpdateReq) {
        User user = userRepo.findById(addressUpdateReq.getUserId()).get();
        user.setAddress(addressUpdateReq.getAddress());
        user.setDistrictId(addressUpdateReq.getDistrictId());
        user.setWardCode(addressUpdateReq.getWardCode());
        userRepo.save(user);
    }

    public StoreResponse sellerRegis(SellerCreationReq sellerCreationReq ) {
        if(userRepo.findByEmail(sellerCreationReq.getEmail()) != null) throw new RuntimeException("User already exists");

        User user = User.builder()
                .email(sellerCreationReq.getEmail())
                .password(sellerCreationReq.getPassword())
                .role(Role.Seller)
                .address(sellerCreationReq.getAddress())
                .districtId(sellerCreationReq.getDistrictId())
                .wardCode(sellerCreationReq.getWardCode())
                .name(sellerCreationReq.getName())
                .phone(sellerCreationReq.getPhone())
                .build();

        userRepo.save(user);

        StoreCreationReq store = StoreCreationReq.builder()
                .name(user.getName())
                .address(user.getAddress())
                .districtId(sellerCreationReq.getDistrictId())
                .wardCode(sellerCreationReq.getWardCode())
                .ownerId(user.getId())
                .build();

        return storeSerivce.createStore(store);
    }

    public Store sellerLogin(UserLoginReq userLoginReq) {
        User user = userRepo.findByEmail(userLoginReq.getAccountName());
        if(user == null) throw new RuntimeException("User not found");
        if (!user.getPassword().equals(userLoginReq.getPassword())) throw new RuntimeException("Incorrect password");
        return storeSerivce.getByUserId(user.getId());
    }
}
