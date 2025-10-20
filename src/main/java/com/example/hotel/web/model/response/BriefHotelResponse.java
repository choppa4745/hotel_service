package com.example.hotel.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BriefHotelResponse {

    private UUID id;
    private String hotelName;
    private String hotelAddress;
    private String title;
    private Integer distanceFromCenter;

    private String timeDistanceFromCenter; // Было Duration

    private Double hotelRating;
    private Integer totalAssessments;
}
