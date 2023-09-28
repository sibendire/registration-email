package com.registrationlogin.registration.appregistration;


import com.registrationlogin.registration.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/joshcode/account")
public class AppUserController {
    private final AppUserService appUserService;
    private  final RegistrationService registrationService;
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
