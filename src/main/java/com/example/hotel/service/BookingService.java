package com.example.hotel.service;

import com.example.hotel.web.model.request.UpsertBookingRequest;
import com.example.hotel.web.model.response.BookingResponse;
import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingResponse createBooking(UpsertBookingRequest request);
    List<BookingResponse> getAllBookings();
    List<BookingResponse> getUserBookings(UUID userId);
    void cancelBooking(UUID bookingId);
}