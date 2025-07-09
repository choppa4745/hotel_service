package com.example.hotel.service;

import com.example.hotel.web.model.request.UpsertRoomRequest;
import com.example.hotel.web.model.response.RoomResponse;
import java.util.List;
import java.util.UUID;

public interface RoomService {
    RoomResponse findById(UUID id);
    List<RoomResponse> findByHotelId(UUID hotelId);
    RoomResponse create(UpsertRoomRequest request);
    RoomResponse update(UUID id, UpsertRoomRequest request);
    void delete(UUID id);
}