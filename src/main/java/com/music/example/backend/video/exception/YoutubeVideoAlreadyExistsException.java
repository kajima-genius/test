package com.music.example.backend.video.exception;

public class YoutubeVideoAlreadyExistsException extends RuntimeException {

    public YoutubeVideoAlreadyExistsException(String message) {
        super(message);
    }
}
