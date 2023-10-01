package com.registrationlogin.registration.appregistration.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ConfirmationTokenRepository extends JpaRepository<TokenConfirmation,Long> {
   // @Override
    Optional<TokenConfirmation> findByToken(String token);
}
