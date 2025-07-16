package com.example.hotel.web.model.response;

import com.example.hotel.entity.BookingStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookingResponse {
    private UUID id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private UUID roomId;
    private String roomName;
    private UUID userId;
    private String username;
    private BookingStatus status;
}