package org.example.apispring.service;

import org.example.apispring.dto.response.UserResponse;
import org.example.apispring.mapper.UserMapper;
import org.example.apispring.model.User;
import org.example.apispring.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

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

    public UserResponse save(User user) {
        return userMapper.toUserResponse(userRepo.save(user));
    }

    public UserResponse findById(String id) {
        return  userMapper.toUserResponse(userRepo.findById(id).get());
    }
}
