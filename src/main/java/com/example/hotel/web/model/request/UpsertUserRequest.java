package com.example.hotel.web.model.request;

import com.example.hotel.entity.Role;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Set;

@Data
public class UpsertUserRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Set<Role> roles;
}