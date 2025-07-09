package com.example.hotel.web.model.response;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class RoomResponse {
    private Long id;
    private String name;
    private String description;
    private String number;
    private Double price;
    private Integer maxOccupancy;
    private List<LocalDate> unavailableDates;
    private Long hotelId;
    private String hotelName;
}