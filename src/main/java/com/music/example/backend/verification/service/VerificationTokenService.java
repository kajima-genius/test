package com.music.example.backend.verification.service;

import com.music.example.backend.user.domain.User;
import com.music.example.backend.verification.domain.VerificationToken;

public interface VerificationTokenService {

    VerificationToken createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
