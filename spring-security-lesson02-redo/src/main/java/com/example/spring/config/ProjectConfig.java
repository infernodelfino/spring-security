package com.example.spring.config;

import com.example.spring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService getUserServiceDetail() {
        return new UserService();
    }


    @Bean
    public PasswordEncoder encode() {
        return NoOpPasswordEncoder.getInstance();
    }
}
