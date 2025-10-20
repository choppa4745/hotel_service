package com.example.hotel.web.controller;

import com.example.hotel.entity.Role;
import com.example.hotel.entity.User;
import com.example.hotel.repository.UserRepository;
import com.example.hotel.service.UserService;
import com.example.hotel.web.model.request.UpsertUserRequest;
import com.example.hotel.web.model.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);
        return ResponseEntity.ok("Registration successful");
    }
}
