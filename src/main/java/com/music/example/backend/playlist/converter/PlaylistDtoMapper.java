package com.music.example.backend.playlist.converter;

import com.music.example.backend.playlist.domain.Playlist;
import com.music.example.backend.playlist.dto.PlaylistDto;
import com.music.example.backend.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "string")
public interface PlaylistDtoMapper {

    PlaylistDtoMapper INSTANCE = Mappers.getMapper(PlaylistDtoMapper.class);

    @Mapping(target = "owner", source = "user")
    @Mapping(target = "id", expression = "java(dto.getId())")
    Playlist toEntity(PlaylistDto dto, User user);

}
