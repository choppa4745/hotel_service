package com.example.hotel.controller;

import com.example.hotel.entity.Hotel;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.EntityService;
import com.example.hotel.exception.AlreadyExistsException;
import com.example.hotel.web.model.request.PaginationRequest;
import com.example.hotel.web.model.request.UpsertHotelRequest;
import com.example.hotel.web.model.response.HotelResponse;
import com.example.hotel.web.model.response.ModelListResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelMapper.hotelToResponse(hotelService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<HotelResponse> create(@Valid @RequestBody UpsertHotelRequest request) {
        if (hotelService.existsByHotelName(request.getHotelName())) {
            throw new AlreadyExistsException("Hotel with this name already exists");
        }

        Hotel hotel = hotelMapper.upsertRequestToHotel(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelToResponse(hotelService.save(hotel)));
    }


    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    @Accessible(checkBy = AccessCheckType.USER, availableForModerator = true)
    public ResponseEntity<HotelResponse> updateUser(@RequestBody UpsertHotelRequest request, @PathVariable UUID id) {
        Hotel updatedHotel = hotelService.update(id, hotelMapper.upsertRequestToHotel(request));

        return ResponseEntity.ok(hotelMapper.hotelToResponse(updatedHotel));
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_MODERAT')")
//    @Accessible(checkBy = AccessCheckType.USER, availableForModerator = true)
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        hotelService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
