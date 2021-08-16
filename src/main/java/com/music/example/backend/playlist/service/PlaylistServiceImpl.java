package com.music.example.backend.playlist.service;

import com.music.example.backend.common.exception.NotFoundException;
import com.music.example.backend.playlist.converter.PlaylistDtoMapper;
import com.music.example.backend.playlist.converter.PlaylistResponseMapper;
import com.music.example.backend.playlist.domain.Playlist;
import com.music.example.backend.playlist.dto.PlaylistDto;
import com.music.example.backend.playlist.repository.PlaylistRepository;
import com.music.example.backend.playlist.response.PlaylistResponse;
import com.music.example.backend.user.domain.User;
import com.music.example.backend.video.converter.YoutubeVideoDtoMapper;
import com.music.example.backend.video.converter.YoutubeVideoResponseMapper;
import com.music.example.backend.video.domain.YoutubeVideo;
import com.music.example.backend.video.dto.YoutubeVideoDto;
import com.music.example.backend.video.response.YoutubeVideoResponse;
import com.music.example.backend.video.service.YoutubeVideoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final YoutubeVideoService videoService;

    private boolean existsVideosInMultiplyPlaylists(YoutubeVideoDto videoDto) {
        YoutubeVideo video = YoutubeVideoDtoMapper.INSTANCE.toEntity(videoDto);
        Long numberOfVideoRepetitions = playlistRepository.findAll().stream()
                .mapToLong(x -> x.getVideos().contains(video) ? 1 : 0)
                .sum();
        return numberOfVideoRepetitions >= 2 ? true : false;
    }

    @Override
    public List<PlaylistResponse> getPlaylistsByUserId(Long userId) {
        return PlaylistResponseMapper.INSTANCE
                .listEntityToResponse(playlistRepository.findByOwnerId(userId));
    }

    @Override
    public PlaylistResponse getPlaylistById(Long playlistId) {
        return PlaylistResponseMapper.INSTANCE.toResponse(playlistRepository.findById(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist with id: " + playlistId + " not found")));
    }

    @Override
    public PlaylistResponse create(PlaylistDto playlistDto, User user) {
        Playlist saved = PlaylistDtoMapper.INSTANCE.toEntity(playlistDto, user);
        return PlaylistResponseMapper.INSTANCE.toResponse(playlistRepository.save(saved));
    }

    @Override
    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public void addVideo(Long playlistId, YoutubeVideoDto videoDto) {
        videoService.addNewVideo(videoDto);
        YoutubeVideo addedVideo = YoutubeVideoDtoMapper.INSTANCE.toEntity(videoDto);
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new NotFoundException("Playlist with id: " + playlistId + " not found"));
        playlist.getVideos().add(addedVideo);
    }

    @Override
    public void removeVideo(Long playlistId, YoutubeVideoDto videoDto) {
        YoutubeVideo removedVideo = YoutubeVideoDtoMapper.INSTANCE.toEntity(videoDto);
        if (existsVideosInMultiplyPlaylists(videoDto))
            videoService.delete(videoDto.getId());
        playlistRepository.findById(playlistId).get().getVideos().remove(removedVideo);
    }

    @Override
    public List<YoutubeVideoResponse> getAllVideoInPlaylist(Long playlistId) {
        return YoutubeVideoResponseMapper.INSTANCE.listEntityToListResponse(
                playlistRepository.findById(playlistId).get().getVideos());
    }
}
