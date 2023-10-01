package com.registrationlogin.registration.appregistration;

import com.registrationlogin.registration.appregistration.tokens.ConfirmationTokenService;
import com.registrationlogin.registration.appregistration.tokens.TokenConfirmation;
import com.registrationlogin.registration.appuser.AppUser;
import com.registrationlogin.registration.appuser.AppUserRole;
import com.registrationlogin.registration.appuser.AppUserService;
import com.registrationlogin.registration.email.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserService appUserService;
    private final EmailSender emailSender;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        String token = appUserService.signUpUser(
                new AppUser(request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
        String link = "http://localhost:8080/api/joshcode/account/confirm?token";
        emailSender.send(request.getEmail(),buildEmail(request.getFirstName(),link));
        return token;
    }



    public String confirmToken(String token) {
        TokenConfirmation tokenConfirmation = (TokenConfirmation) confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));
        if (tokenConfirmation.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = tokenConfirmation.getExpiredAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedToken(token);
        appUserService.enableAppUser(tokenConfirmation.
                getAppUser().
                getEmail());
        return "Confirm";
    }
    private String buildEmail(String firstName, String link) {
    }
}