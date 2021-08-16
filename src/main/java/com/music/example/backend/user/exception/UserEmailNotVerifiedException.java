package com.music.example.backend.user.exception;

public class UserEmailNotVerifiedException extends RuntimeException{

    public UserEmailNotVerifiedException(String message) {
        super(message);
    }
}
