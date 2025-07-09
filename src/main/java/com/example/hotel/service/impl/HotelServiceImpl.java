package com.example.hotel.service.impl;

import com.example.hotel.entity.Hotel;
import com.example.hotel.exception.AlreadyExistsException;
import com.example.hotel.exception.EntityNotFoundException;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository repository;

    // Реализация базового CRUD
    @Override
    public Page<Hotel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Hotel findById(UUID id) {
        log.info("Find hotel by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Hotel with ID {0} not found!", id)
                ));
    }

    @Override
    public Hotel save(Hotel entity) {
        log.info("Saving hotel: {}", entity);
        return repository.save(entity);
    }

    @Override
    @Transactional
    public Hotel update(UUID id, Hotel entity) {
        log.info("Updating hotel with ID: {}", id);
        Hotel existingHotel = findById(id);

        updateHotelFields(existingHotel, entity);
        log.info("Updated hotel: {}", existingHotel);

        return repository.save(existingHotel);
    }

    @Override
    public void deleteById(UUID id) {
        log.info("Deleting hotel with ID: {}", id);
        repository.deleteById(id);
    }

    // Специфичные методы для отелей
    @Override
    public Hotel findByHotelName(String hotelName) {
        return repository.findByHotelName(hotelName)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Hotel with name {0} not found!", hotelName)
                ));
    }

    @Override
    public boolean existsByHotelName(String hotelName) {
        return repository.existsByHotelName(hotelName);
    }

    // Внутренний метод для обновления полей
    private void updateHotelFields(Hotel existing, Hotel updated) {
        if (!Objects.equals(existing.getHotelName(), updated.getHotelName())) {
            if (existsByHotelName(updated.getHotelName())) {
                throw new AlreadyExistsException(
                        MessageFormat.format("Hotel with name {0} already exists!", updated.getHotelName())
                );
            }
            existing.setHotelName(updated.getHotelName());
        }

        existing.setHotelAddress(updated.getHotelAddress());
        existing.setDistanceFromCenter(updated.getDistanceFromCenter());
        existing.setTimeDistanceFromCenter(updated.getTimeDistanceFromCenter());
    }
}