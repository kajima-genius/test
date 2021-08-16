package com.music.example.backend.user.dto;

import com.music.example.backend.user.domain.Gender;
import com.music.example.backend.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private Gender gender;
    private Long id;
    private String email;
    private String password;
    private Role role;
    private String dateOfBirth;

    public UserDto(String username, Gender gender, String email, String password, String dateOfBirth) {
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}