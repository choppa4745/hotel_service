package com.example.hotel.web.model.response;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {

    private UUID id;

    private String hotelName;

    private String hotelAddress;

    private String title;

    private Integer distanceFromCenter;

    private Duration timeDistanceFromCenter;

    private Double hotelRating;

    private List<Integer> assessments;
}
