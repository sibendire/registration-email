package com.registrationlogin.registration.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor

public class EmailService implements EmailSender{
    private final  static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;
    @Override
    public void send(String to, String email) {
        try {

        }catch (MessagingException e){
            LOGGER.error("failed to send the email ", e);
        }throw new IllegalStateException("failed");

    }
}
