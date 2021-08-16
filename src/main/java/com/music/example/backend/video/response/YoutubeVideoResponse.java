package com.music.example.backend.video.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YoutubeVideoResponse {

    private String youtubeId;
    private String channelId;
    private String title;
    private String imageUrl;
    private Long viewCount;
    private String channelTitle;
    private Long timestamp;
}
