package com.music.example.backend.common.security.endcoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {

    @Value("${min.log}")
    private int MIN_LOG;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(MIN_LOG);
    }
}