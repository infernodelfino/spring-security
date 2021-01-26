package com.example.service;

import com.example.entities.User;
import com.example.repository.UserRepository;
import com.example.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        User currentUser = existingUser.orElseThrow(() -> new UsernameNotFoundException("No user with ID"));

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> existingUser = userRepository.findUserByUsername(username);
        User user = existingUser.orElseThrow(() -> {
            return new UsernameNotFoundException("User with username doesn't exist");
        });

        return new SecurityUser(user);
    }
}
