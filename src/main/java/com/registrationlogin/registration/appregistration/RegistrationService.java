package com.registrationlogin.registration.appregistration;

import com.registrationlogin.registration.appuser.AppUser;
import com.registrationlogin.registration.appuser.AppUserRole;
import com.registrationlogin.registration.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(request.getFirstName(),
                        request.getLastName(),
                request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}