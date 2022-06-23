package org.grube.springsecurityregistrationlogin.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.grube.springsecurityregistrationlogin.appuser.AppUser;
import org.grube.springsecurityregistrationlogin.appuser.AppUserRole;
import org.grube.springsecurityregistrationlogin.appuser.AppUserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    //todo: create Exception Handler
    public String register(RegistrationRequest request) {
        String requestedEmail = request.getEmail();
        boolean isValidEmail = emailValidator.test(requestedEmail);
        if (!isValidEmail) {
            log.info(String.format("Email %s is not valid.", requestedEmail));
//            throw new IllegalArgumentException("requestedEmail not valid");
        }
        AppUser userToRegister = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                requestedEmail,
                request.getPassword(),
                AppUserRole.USER);
        AppUser registeredUser = appUserService.signUpAppUser(userToRegister);
        return "Registered user: " + registeredUser;
    }
}
