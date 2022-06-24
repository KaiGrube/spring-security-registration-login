package org.grube.springsecurityregistrationlogin.security.config;

import lombok.AllArgsConstructor;
import org.grube.springsecurityregistrationlogin.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AppUserService appUserService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    @Order(1)
    SecurityFilterChain defaultService(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/v1/secured")
            .authenticated()
            .and()
            .formLogin().permitAll() // .formLogin(form -> form.loginPage("/api/v1/login").permitAll()); // redirect to custom login url
            .and()
            .authorizeRequests()
            .antMatchers("/api/v1/open", "/api/v1/logout").permitAll();
        return http.build();
    }

//    @Bean
//    @Order(2)
//    SecurityFilterChain openService(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/api/v1/open").permitAll();
//        return http.build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().antMatchers("/api/v*/registration/**", "/api/v1/open");
//    }
}




