package com.music.example.backend.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class YoutubeVideoDto {

    private Long id;
    private String youtubeId;
    private String channelId;
    private String title;
    private String imageUrl;
    private Long viewCount;
    private String channelTitle;
    private Long timestamp;
}
