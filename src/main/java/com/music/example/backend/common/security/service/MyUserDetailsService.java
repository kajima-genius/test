package com.music.example.backend.common.security.service;

import com.music.example.backend.common.exception.NotFoundException;
import com.music.example.backend.common.security.CustomUserPrincipal;
import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.exception.UserEmailNotVerifiedException;
import com.music.example.backend.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email='" + email + "' not found"));
        if (!user.isEnabled()) {
            throw new UserEmailNotVerifiedException("Email not verified");
        }
        return new CustomUserPrincipal(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id" + id + "not found"));

        return new CustomUserPrincipal(user);
    }
}
