package com.example.hotel.configuration;

import com.example.hotel.entity.Role;
import com.example.hotel.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                antMatcher("/v3/api-docs/**"),
                                antMatcher("/swagger-ui/**"),
                                antMatcher("/swagger-ui.html")
                        ).permitAll()
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/hotels/**", "/api/rooms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/hotels/**", "/api/rooms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/hotels/**", "/api/rooms/**").hasRole("ADMIN")
                        .requestMatchers("/api/bookings/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        // Временный пользователь для тестирования
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin")) // Пароль "admin" будет закодирован
                .roles("ADMIN", "USER") // Добавляем роли
                .build();

        // Вы можете добавить других тестовых пользователей
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
