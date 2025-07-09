package com.example.hotel.mapper;

import com.example.hotel.entity.Hotel;
import com.example.hotel.web.model.request.UpsertHotelRequest;
import com.example.hotel.web.model.response.HotelEditResponse;
import com.example.hotel.web.model.response.HotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.MappingConstants.ComponentModel;


@Mapper(componentModel = "spring")
public interface HotelMapper {
//
//    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
//    @Mapping(target = "hotelRating", expression = "java(0.0)")
//    @Mapping(target = "assessments", expression = "java(new ArrayList<>())")
    Hotel upsertRequestToHotel(UpsertHotelRequest request);

    HotelEditResponse hotelToEditResponse(Hotel hotel);
    HotelResponse hotelToResponse(Hotel hotel);
}
