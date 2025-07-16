package com.example.hotel.web.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class RoomResponse {
    private UUID id;
    private String name;
    private String description;
    private String number;
    private Double price;
    private Integer maxOccupancy;
    private List<LocalDate> unavailableDates;
    private UUID hotelId;
    private String hotelName;
}