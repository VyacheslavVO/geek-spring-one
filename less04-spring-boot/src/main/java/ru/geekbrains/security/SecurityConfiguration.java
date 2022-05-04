package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    protected void authConfig(AuthenticationManagerBuilder auth,
                              UserDetailsService userDetailsService,
                              PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password(encoder.encode("pass1"))
                .roles("ADMIN")
                .and()
                .withUser("user2")
                .password(encoder.encode("pass2"))
                .roles("GUEST");

        auth.userDetailsService((userDetailsService));
    }
}
