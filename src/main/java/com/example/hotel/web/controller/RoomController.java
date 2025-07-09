package com.example.hotel.web.controller;

import com.example.hotel.service.RoomService;
import com.example.hotel.web.model.request.UpsertRoomRequest;
import com.example.hotel.web.model.response.RoomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRoomsInHotel(@PathVariable UUID hotelId) {
        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponse> getRoomById(
            @PathVariable UUID hotelId,
            @PathVariable UUID roomId) {
        return ResponseEntity.ok(roomService.findById(roomId));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(
            @PathVariable UUID hotelId,
            @RequestBody @Valid UpsertRoomRequest request) {
        request.setHotelId(hotelId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.create(request));
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable UUID hotelId,
            @PathVariable UUID roomId,
            @RequestBody @Valid UpsertRoomRequest request) {
        request.setHotelId(hotelId);
        return ResponseEntity.ok(roomService.update(roomId, request));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable UUID hotelId,
            @PathVariable UUID roomId) {
        roomService.delete(roomId);
        return ResponseEntity.noContent().build();
    }
}