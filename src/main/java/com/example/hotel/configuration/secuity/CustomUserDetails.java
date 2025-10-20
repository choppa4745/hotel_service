package com.example.hotel.configuration.secuity;

import com.example.hotel.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name())) // <-- name() превращает enum в String
                .collect(Collectors.toSet());
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // или добавить поле в User
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // или добавить поле в User
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // или добавить поле в User
    }

    @Override
    public boolean isEnabled() {
        return true; // или добавить поле в User
    }
}
