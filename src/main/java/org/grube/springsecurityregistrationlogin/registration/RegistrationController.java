package org.grube.springsecurityregistrationlogin.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        log.info(request.toString());
        return registrationService.register(request);
    }

    @GetMapping
    public String test() {
        return "it works";
    }
}
