package com.example.hotel.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "app_hotel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@FieldNameConstants
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String hotelName;

    @Column(nullable = false, unique = true)
    private String hotelAddress;

    @Column
    private String title;

    @Column(nullable = false)
    private Integer distanceFromCenter;

    @Column(nullable = false)
    private Duration timeDistanceFromCenter;

    @Column
    private Double hotelRating;

    @ElementCollection
    private List<Integer> assessments = new ArrayList<>();
}
