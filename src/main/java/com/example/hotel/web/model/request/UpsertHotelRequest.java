package com.example.hotel.web.model.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.Duration;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertHotelRequest {

    @NotNull
    @Size(min = 1, max = 20, message = "Min size of hotel name is: {min}. Max size is: {max}")
    private String hotelName;

    @NotNull
    private String hotelAddress;

    @Size(min = 1, max = 255, message = "Min title size is: {min}. Max title is: {max}")
    private String title;

    private Integer distanceFromCenter;

    private Duration timeDistanceFromCenter;
}

