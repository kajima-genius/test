package com.music.example.backend.user.response;

import com.music.example.backend.user.domain.Gender;
import com.music.example.backend.user.domain.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private String username;
    private Gender gender;
    private Long id;
    private String email;
    private Role role;
    private Date dateOfBirth;
    private boolean enabled;
}
