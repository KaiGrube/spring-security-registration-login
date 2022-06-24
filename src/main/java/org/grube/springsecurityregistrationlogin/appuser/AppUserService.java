package org.grube.springsecurityregistrationlogin.appuser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.springsecurityregistrationlogin.registration.token.ConfirmationToken;
import org.grube.springsecurityregistrationlogin.registration.token.ConfirmationTokenRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpAppUser(AppUser userToSignUp) {
        boolean userExists = appUserRepository.findByEmail(userToSignUp.getEmail()).isPresent();
        if (userExists) throw new IllegalStateException("email already exists");
        String encodedPassword = bCryptPasswordEncoder.encode(userToSignUp.getPassword());
        userToSignUp.setPassword(encodedPassword);
        AppUser signedUpUser = appUserRepository.save(userToSignUp);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1),
                signedUpUser);
        confirmationTokenRepository.save(confirmationToken); // todo: use return value to check operation
        // todo: isolate token generation from here; is also needed for login
        log.info(String.format("New user signed up: %s", signedUpUser));
        return token;
    }
}
