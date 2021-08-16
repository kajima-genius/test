package com.music.example.backend.user.controller;

import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.dto.UserDto;
import com.music.example.backend.user.response.UserResponse;
import com.music.example.backend.user.service.UserService;
import com.music.example.backend.verification.OnRegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationEventPublisher eventPublisher;
    private final UserService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/registration")
    public ResponseEntity<UserResponse> create(@RequestBody UserDto userDto,
                                               HttpServletRequest request) {
        UserResponse response = service.create(userDto);
        User user = new User();
        user.setId(response.getId());
        user.setEmail(userDto.getEmail());
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
        return ResponseEntity.created(URI.create("/users" + response.getId())).body(response);
    }

    @GetMapping("/current")
    public ResponseEntity<UserResponse> getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponse response = service.getUserByEmail(userEmail);
        return ResponseEntity.ok(response);
    }
}
