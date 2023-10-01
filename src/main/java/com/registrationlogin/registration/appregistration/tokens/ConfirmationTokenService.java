package com.registrationlogin.registration.appregistration.tokens;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public void saveConfirmationToken(TokenConfirmation tokenConfirmation){
        confirmationTokenRepository.save(tokenConfirmation);
    }
}
