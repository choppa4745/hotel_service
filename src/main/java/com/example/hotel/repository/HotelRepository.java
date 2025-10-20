package com.example.hotel.repository;

import com.example.hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    Optional<Hotel> findByHotelName(String hotelName);

    boolean existsByHotelName(String EventName);
}

