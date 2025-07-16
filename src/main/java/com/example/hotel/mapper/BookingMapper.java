package com.example.hotel.mapper;

import com.example.hotel.entity.Booking;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;
import com.example.hotel.web.model.request.UpsertBookingRequest;
import com.example.hotel.web.model.response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "room", source = "roomId", qualifiedByName = "mapRoomIdToRoom")
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    Booking bookingRequestToBooking(UpsertBookingRequest request);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "roomName", source = "room.name")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    BookingResponse bookingToBookingResponse(Booking booking);

    @Named("mapRoomIdToRoom")
    default Room mapRoomIdToRoom(UUID roomId) {
        if (roomId == null) {
            return null;
        }
        Room room = new Room();
        room.setId(roomId);
        return room;
    }

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(UUID userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }
}
