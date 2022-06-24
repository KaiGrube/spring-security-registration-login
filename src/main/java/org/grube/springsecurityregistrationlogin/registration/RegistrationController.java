package org.grube.springsecurityregistrationlogin.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
@AllArgsConstructor
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(path = "/registration")
    public String register(@RequestBody RegistrationRequest request) {
        log.info(request.toString());
        return registrationService.register(request);
    }
}
