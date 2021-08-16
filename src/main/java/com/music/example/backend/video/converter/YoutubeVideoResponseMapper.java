package com.music.example.backend.video.converter;


import com.music.example.backend.video.domain.YoutubeVideo;
import com.music.example.backend.video.response.YoutubeVideoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "string")
public interface YoutubeVideoResponseMapper {

    YoutubeVideoResponseMapper INSTANCE = Mappers.getMapper(YoutubeVideoResponseMapper.class);

    YoutubeVideoResponse toResponse(YoutubeVideo entity);

    List<YoutubeVideoResponse> listEntityToListResponse(List<YoutubeVideo> entities);
}
