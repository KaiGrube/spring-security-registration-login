package org.grube.springsecurityregistrationlogin.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.springsecurityregistrationlogin.appuser.AppUser;
import org.grube.springsecurityregistrationlogin.appuser.AppUserRepository;
import org.grube.springsecurityregistrationlogin.appuser.AppUserRole;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserRepository appUserRepository;

    //todo: create Exception Handler
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            log.info("email is not valid.");
//            throw new IllegalArgumentException("email not valid");
        }
        appUserRepository.findByEmail(request.getEmail()).ifPresent(email -> {
            log.info("email already exists.");
            throw new IllegalStateException("email already exists");
        });

        AppUser appUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER);
        appUserRepository.save(appUser);
        return "new appUser registered: " + appUser;
    }
}
