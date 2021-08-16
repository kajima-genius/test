package com.music.example.backend.playlist.converter;


import com.music.example.backend.playlist.domain.Playlist;
import com.music.example.backend.playlist.response.PlaylistResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "string")
public interface PlaylistResponseMapper {

    PlaylistResponseMapper INSTANCE = Mappers.getMapper(PlaylistResponseMapper.class);

    @Mapping(target = "userId", expression = "java(entity.getOwner().getId())")
    PlaylistResponse toResponse(Playlist entity);

    List<PlaylistResponse> listEntityToResponse(List<Playlist> entities);
}
