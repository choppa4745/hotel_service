package com.example.hotel.web.controller;

import com.example.hotel.entity.Role;
import com.example.hotel.service.UserService;
import com.example.hotel.web.model.request.UpsertUserRequest;
import com.example.hotel.web.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody @Valid UpsertUserRequest request) {
        request.setRoles(Set.of(Role.USER));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(request));
    }
}