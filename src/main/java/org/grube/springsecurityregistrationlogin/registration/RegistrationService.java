package org.grube.springsecurityregistrationlogin.registration;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return request.toString();
    }
}
