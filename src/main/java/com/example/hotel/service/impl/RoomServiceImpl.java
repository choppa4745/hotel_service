package com.example.hotel.service.impl;

import com.example.hotel.entity.Room;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.mapper.RoomMapper;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.RoomService;
import com.example.hotel.web.model.request.UpsertRoomRequest;
import com.example.hotel.web.model.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelService hotelService;
    private final RoomMapper roomMapper;

    @Override
    public RoomResponse findById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
        return roomMapper.roomToRoomResponse(room);
    }

    @Override
    public List<RoomResponse> findByHotelId(UUID hotelId) {
        hotelService.findById(hotelId); // Проверяем существование отеля
        return roomRepository.findByHotelId(hotelId).stream()
                .map(roomMapper::roomToRoomResponse)
                .toList();
    }

    @Override
    @Transactional
    public RoomResponse create(UpsertRoomRequest request) {
        if (roomRepository.existsByNumberAndHotelId(request.getNumber(), request.getHotelId())) {
            throw new IllegalArgumentException(
                "Room with number " + request.getNumber() + " already exists in this hotel"
            );
        }

        Room room = roomMapper.roomRequestToRoom(request);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.roomToRoomResponse(savedRoom);
    }

    @Override
    @Transactional
    public RoomResponse update(UUID id, UpsertRoomRequest request) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));

        // Проверяем уникальность номера комнаты в отеле
        if (!existingRoom.getNumber().equals(request.getNumber())){
            if (roomRepository.existsByNumberAndHotelId(request.getNumber(), request.getHotelId())) {
                throw new IllegalArgumentException(
                    "Room with number " + request.getNumber() + " already exists in this hotel"
                );
            }
        }

        roomMapper.updateRoomFromRequest(request, existingRoom);
        Room updatedRoom = roomRepository.save(existingRoom);
        return roomMapper.roomToRoomResponse(updatedRoom);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!roomRepository.existsById(id)) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }
}