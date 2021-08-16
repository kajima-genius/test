package com.music.example.backend.video.service;

import com.music.example.backend.video.dto.YoutubeVideoDto;
import com.music.example.backend.video.response.YoutubeVideoResponse;

import java.util.List;

public interface YoutubeVideoService {

    List<YoutubeVideoResponse> searchYoutubeVideo(String queryTerm);

    List<YoutubeVideoResponse> getYoutubeVideoTrends();

    YoutubeVideoResponse addNewVideo(YoutubeVideoDto youtubeVideoDto);

    YoutubeVideoResponse getYoutubeVideo(String youtubeId);

    void delete(Long id);
}
