package org.grube.springsecurityregistrationlogin.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) { // todo: make method return value
        confirmationTokenRepository.save(token);
    }
}
