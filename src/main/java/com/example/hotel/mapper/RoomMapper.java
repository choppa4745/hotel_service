package com.example.hotel.mapper;

import com.example.hotel.entity.Hotel;
import com.example.hotel.entity.Room;
import com.example.hotel.web.model.request.UpsertRoomRequest;
import com.example.hotel.web.model.response.RoomResponse;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", source = "hotelId", qualifiedByName = "mapHotelIdToHotel")
    Room roomRequestToRoom(UpsertRoomRequest request);

    @Mapping(target = "hotelId", source = "hotel.id")
    @Mapping(target = "hotelName", source = "hotel.name")
    RoomResponse roomToRoomResponse(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hotel", ignore = true)
    void updateRoomFromRequest(UpsertRoomRequest request, @MappingTarget Room room);

    @Named("mapHotelIdToHotel")
    default Hotel mapHotelIdToHotel(UUID hotelId) {
        if (hotelId == null) {
            return null;
        }
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        return hotel;
    }
}