package com.example.hotel.mapper;

import com.example.hotel.entity.User;
import com.example.hotel.web.model.request.UpsertUserRequest;
import com.example.hotel.web.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse userToUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    User upsertUserRequestToUser(UpsertUserRequest request);
}