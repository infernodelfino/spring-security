package com.example.springsecurityregistrationloginMySQLthymeleaf.service;

import com.example.springsecurityregistrationloginMySQLthymeleaf.dto.UserRegistrationDto;
import com.example.springsecurityregistrationloginMySQLthymeleaf.entities.Role;
import com.example.springsecurityregistrationloginMySQLthymeleaf.entities.User;
import com.example.springsecurityregistrationloginMySQLthymeleaf.repository.UserRepository;
import com.example.springsecurityregistrationloginMySQLthymeleaf.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User();

        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("invalid email");
        }

        return new SecurityUser(user);
    }

    /*
    * map a collections of role to a collection of <? extends GrantedAuthority>
    *
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    *
    */
}
