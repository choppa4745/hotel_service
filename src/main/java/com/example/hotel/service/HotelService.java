package com.example.hotel.service;

import com.example.hotel.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface HotelService {
    // Базовый CRUD функционал из EntityService
    Page<Hotel> findAll(Pageable pageable);
    Hotel findById(UUID id);
    Hotel save(Hotel entity);
    Hotel update(UUID id, Hotel entity);
    void deleteById(UUID id);

    // Специфичные методы для отелей
    Hotel findByHotelName(String hotelName);
    boolean existsByHotelName(String hotelName);
}