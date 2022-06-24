package org.grube.springsecurityregistrationlogin.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// only for testing purposes | todo: remove later
@RestController
//@RequestMapping(path = "api/v1")
public class testController {
    @GetMapping(path = "/api/v1/secured")
    public String secured() {
        return "secured";
    }
    @GetMapping(path = "/api/v1/open")
    public String open() {
        return "open";
    }
    @GetMapping(path = "/api/v1/logout")
    public String logout() {
        return "logout";
    }
}
