package com.example.hotel.web.model.response;

import com.example.hotel.entity.Role;
import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String username;
    private String email;
    private Set<Role> roles;
}