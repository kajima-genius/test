package com.music.example.backend.verification.controller;

import com.music.example.backend.user.domain.User;
import com.music.example.backend.user.service.UserService;
import com.music.example.backend.verification.domain.VerificationToken;
import com.music.example.backend.verification.service.VerificationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Calendar;
import java.util.Locale;

@AllArgsConstructor
@Controller
public class VerificationController {

    private final UserService userService;
    private final MessageSource messages;
    private final VerificationTokenService tokenService;

    @GetMapping("/registrationConfirm")
    public RedirectView confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {
        Locale locale = request.getLocale();

        VerificationToken verificationToken = tokenService.getVerificationToken(token);

        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", message);
            return new RedirectView("http://localhost:8080/badUser");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if (verificationToken.getExpiryDate().before(cal.getTime())) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return new RedirectView("http://localhost:8080/badUser");
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return new RedirectView("http://localhost:3000/login");
    }

}
