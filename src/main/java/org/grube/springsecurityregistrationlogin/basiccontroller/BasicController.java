package org.grube.springsecurityregistrationlogin.basiccontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "api/v1")
public class BasicController {
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
