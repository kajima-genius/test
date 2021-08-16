package com.music.example.backend.playlist.controller;

import com.music.example.backend.playlist.dto.PlaylistDto;
import com.music.example.backend.playlist.response.PlaylistResponse;
import com.music.example.backend.playlist.service.PlaylistService;
import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.service.UserService;
import com.music.example.backend.video.dto.YoutubeVideoDto;
import com.music.example.backend.video.response.YoutubeVideoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserService userService;

    @PutMapping(value = "/{playlistId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<YoutubeVideoResponse>> addVideoToPlaylist(
            @PathVariable("playlistId") Long playlistId,
            @RequestBody YoutubeVideoDto videoDto) {

        playlistService.addVideo(playlistId, videoDto);
        List<YoutubeVideoResponse> videos = playlistService.getAllVideoInPlaylist(playlistId);
        return ResponseEntity.ok(videos);
    }

    @DeleteMapping(value = "/{playlistId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<YoutubeVideoResponse>> removeVideoFromPlaylist(
            @PathVariable("playlistId") Long playlistId,
            @RequestBody YoutubeVideoDto videoDto) {

        playlistService.removeVideo(playlistId, videoDto);
        List<YoutubeVideoResponse> videos = playlistService.getAllVideoInPlaylist(playlistId);
        return ResponseEntity.ok(videos);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PlaylistResponse> create(@RequestBody PlaylistDto playlistDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PlaylistResponse response = playlistService.create(playlistDto, user);
        return ResponseEntity.created(URI.create("/playlists" + response.getId())).body(response);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<PlaylistResponse>> getAllPlaylists() {
        Long userId = userService.getUserByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName())
                .getId();
        List<PlaylistResponse> response = playlistService.getPlaylistsByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
