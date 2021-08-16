package com.music.example.backend.video.converter;

import com.music.example.backend.video.domain.YoutubeVideo;
import com.music.example.backend.video.dto.YoutubeVideoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "string")
public interface YoutubeVideoDtoMapper {

    YoutubeVideoDtoMapper INSTANCE = Mappers.getMapper(YoutubeVideoDtoMapper.class);

    YoutubeVideo toEntity(YoutubeVideoDto dto);
}
