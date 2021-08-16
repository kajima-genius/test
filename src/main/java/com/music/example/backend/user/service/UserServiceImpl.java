package com.music.example.backend.user.service;

import com.music.example.backend.common.exception.NotFoundException;
import com.music.example.backend.user.converter.UserDtoMapper;
import com.music.example.backend.user.converter.UserResponseMapper;
import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.dto.UserDto;
import com.music.example.backend.user.exception.UserAlreadyExistException;
import com.music.example.backend.user.repository.UserRepository;
import com.music.example.backend.user.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserResponse create(UserDto dto) {
        if (emailExist(dto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + dto.getEmail());
        }

        String encodedPassword = encoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);
        User newUser = UserDtoMapper.INSTANCE.toEntity(dto);
        return UserResponseMapper.INSTANCE.toResponse(userRepository.save(newUser));
    }

    @Override
    public void processOAuthPostLogin(OAuth2User oAuth2User) {
        if (!emailExist(oAuth2User.getAttribute("email"))) {
            User newUser = new User();
            newUser.setEmail(oAuth2User.getAttribute("email"));
            newUser.setEnabled(true);
            userRepository.save(newUser);
        }
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User entity = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email" + email + "not found"));
        UserResponse response = UserResponseMapper.INSTANCE.toResponse(entity);
        return response;
    }
}
