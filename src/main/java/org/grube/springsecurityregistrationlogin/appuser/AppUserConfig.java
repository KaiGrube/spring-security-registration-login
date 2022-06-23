package org.grube.springsecurityregistrationlogin.appuser;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AppUserConfig {

    private final AppUserService appUserService;

    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
        return args -> {

            AppUser admin = new AppUser(
                    "Anton",
                    "Administri",
                    "anton@admin.com",
                    "admin",
                    AppUserRole.ADMIN);

            AppUser user = new AppUser(
                    "Ulf",
                    "Useri",
                    "ulf@user.com",
                    "user",
                    AppUserRole.USER);

//            appUserRepository.saveAll(List.of(admin, user));
            appUserService.signUpAppUser(admin);
            appUserService.signUpAppUser(user);

        };
    }
}
