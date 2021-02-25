package com.example.springsecurityregistrationloginMySQLthymeleaf.service;

import com.example.springsecurityregistrationloginMySQLthymeleaf.dto.UserRegistrationDto;
import com.example.springsecurityregistrationloginMySQLthymeleaf.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    public User save(UserRegistrationDto userRegistrationDto);

}
