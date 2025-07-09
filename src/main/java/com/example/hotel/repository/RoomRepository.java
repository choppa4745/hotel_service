package com.example.hotel.repository;

import com.example.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    List<Room> findByHotelId(UUID hotelId);
    boolean existsByNumberAndHotelId(String number, UUID hotelId);
}