package com.example.hotel.service.impl;

import com.example.hotel.configuration.secuity.CustomUserDetails;
import com.example.hotel.entity.User;
import com.example.hotel.exception.EntityNotFoundException;
import com.example.hotel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws EntityNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new CustomUserDetails(user);
    }
}
