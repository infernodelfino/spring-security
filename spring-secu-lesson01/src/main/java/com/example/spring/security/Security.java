package com.example.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Security {

    @Bean
    public UserDetailsService getUserDetail() {
        InMemoryUserDetailsManager userDetailService = new InMemoryUserDetailsManager();
        UserDetails u1 = User.withUsername("Sa")
                .password("123")
                .authorities("read")
                .build();

        userDetailService.createUser(u1);

        return userDetailService;
    }

    @Bean
    public PasswordEncoder encodePassword() {

        return NoOpPasswordEncoder.getInstance();
    }

}
