package com.example.spring.service;

import com.example.spring.entities.User;
import com.example.spring.repository.UserRepository;
import com.example.spring.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existingUser = userRepository.findUserByUsername(username);
        User user = existingUser.orElseThrow(() ->{

         throw new UsernameNotFoundException("No username");
        });

        return new SecurityUser(user);
    }
}
