package com.music.example.backend.playlist.service;

import com.music.example.backend.playlist.dto.PlaylistDto;
import com.music.example.backend.playlist.response.PlaylistResponse;
import com.music.example.backend.user.domain.User;
import com.music.example.backend.video.dto.YoutubeVideoDto;
import com.music.example.backend.video.response.YoutubeVideoResponse;

import java.util.List;

public interface PlaylistService {

    List<PlaylistResponse> getPlaylistsByUserId(Long userId);

    PlaylistResponse getPlaylistById(Long playlistId);

    PlaylistResponse create(PlaylistDto playlistDto, User user);

    List<YoutubeVideoResponse> getAllVideoInPlaylist(Long playlistId);

    void delete(Long id);

    void addVideo(Long playlistId, YoutubeVideoDto videoDto);

    void removeVideo(Long playlistId, YoutubeVideoDto videoDto);
}
