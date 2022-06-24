package org.grube.springsecurityregistrationlogin.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "api/v1")

@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(path = "/registration")
    public String register(@RequestBody RegistrationRequest request) {
        log.info(request.toString());
        return registrationService.register(request);
    }
}
