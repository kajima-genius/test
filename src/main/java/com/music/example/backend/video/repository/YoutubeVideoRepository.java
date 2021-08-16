package com.music.example.backend.video.repository;

import com.music.example.backend.video.domain.YoutubeVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideo, Long> {

    Optional<YoutubeVideo> findByYoutubeId(String youtubeId);
}
