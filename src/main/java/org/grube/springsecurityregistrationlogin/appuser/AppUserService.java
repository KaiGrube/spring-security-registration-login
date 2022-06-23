package org.grube.springsecurityregistrationlogin.appuser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUser signUpAppUser(AppUser userToSignUp) {
        boolean userExists = appUserRepository.findByEmail(userToSignUp.getEmail()).isPresent();
        if (userExists) throw new IllegalStateException("email already exists");
        String encodedPassword = bCryptPasswordEncoder.encode(userToSignUp.getPassword());
        userToSignUp.setPassword(encodedPassword);
        AppUser signedUpUser = appUserRepository.save(userToSignUp);
        log.info(String.format("New user signed up: %s", signedUpUser));
        return signedUpUser;
    }
}
