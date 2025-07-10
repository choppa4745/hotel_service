package com.example.hotel.service;

import com.example.hotel.web.model.request.UpsertUserRequest;
import com.example.hotel.web.model.response.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(UpsertUserRequest request);
    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UUID id, UpsertUserRequest request);
    void deleteUser(UUID id);
    UserResponse findByUsername(String username);
    UserResponse findByEmail(String email);

}