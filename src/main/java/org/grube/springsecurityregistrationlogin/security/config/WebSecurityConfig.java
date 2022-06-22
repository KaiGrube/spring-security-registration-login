package org.grube.springsecurityregistrationlogin.security.config;

import lombok.AllArgsConstructor;
import org.grube.springsecurityregistrationlogin.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

// todo: find alternative for deprecated WebSecurityConfigurerAdapter
// => read: https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
// Spring Security without the WebSecurityConfigurerAdapter
// In Spring Security 5.7.0-M2 we deprecated the WebSecurityConfigurerAdapter,
// as we encourage users to move towards a component-based security configuration.

//@Configuration
//@AllArgsConstructor
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//        http
//        .csrf().disable()
//        .authorizeRequests()
//        .antMatchers("/api/v*/registration/**")
//        .permitAll()
//        .anyRequest()
//        .authenticated().and()
//        .formLogin();
//        }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//}

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
        http.authorizeHttpRequests()
                .antMatchers("/api/v*/registration/**", "/api/v1/hello/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/api/v*/registration/**", "/api/v1/hello/**");
    }
}




