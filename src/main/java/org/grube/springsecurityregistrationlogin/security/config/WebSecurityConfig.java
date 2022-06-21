package org.grube.springsecurityregistrationlogin.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// todo: find alternative for deprecated WebSecurityConfigurerAdapter
// => read: https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapterSpring
// Security without the WebSecurityConfigurerAdapter
// In Spring Security 5.7.0-M2 we deprecated the WebSecurityConfigurerAdapter,
// as we encourage users to move towards a component-based security configuration.

//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HTTPSecurity http) throws Exception {
//    }
//}


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
                .httpBasic(withDefaults());
        return http.build();
    }

}




