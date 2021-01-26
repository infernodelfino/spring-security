package com.example.config;

import com.example.security.CustomUserDetailsManager;
import com.example.security.SecurityUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsManager userDetailsManager() {
        /*
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails users = User
                .withUsername("sa")
                .password("123")
                .authorities("ADMIN")
                .build();
         */


        CustomUserDetailsManager detailsManager = new CustomUserDetailsManager();
        detailsManager.createUser(new SecurityUser("sa", "123"));
        return detailsManager;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().anyRequest().authenticated();
    }

}
