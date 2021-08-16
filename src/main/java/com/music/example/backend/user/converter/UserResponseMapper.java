package com.music.example.backend.user.converter;

import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "string")
public interface UserResponseMapper {

   UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

   UserResponse toResponse(User entity);
}
