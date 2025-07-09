package com.example.hotel.web.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpsertRoomRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String number;

    @Positive
    private Double price;

    @Min(1)
    private Integer maxOccupancy;

    private List<LocalDate> unavailableDates;

    @NotNull
    private UUID hotelId;
}