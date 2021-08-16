package com.music.example.backend.user.service;

import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.dto.UserDto;
import com.music.example.backend.user.response.UserResponse;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {

    void saveRegisteredUser(User user);

    UserResponse create(UserDto dto);

    void processOAuthPostLogin(OAuth2User oAuth2User);

    UserResponse getUserByEmail(String email);
}
