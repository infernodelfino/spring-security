package com.example.spring.security.providers;

import com.example.spring.entities.Otp;
import com.example.spring.repository.OtpRepository;
import com.example.spring.security.authentications.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OtpRepository otpRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        Optional<Otp> otpToken = otpRepository.findOtpByUsername(username);

        if (otpToken.isPresent()) {
            return new OtpAuthentication(username, otp, List.of(() -> "read"));
        }
        throw new BadCredentialsException("Bad otp is bad");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.equals(authentication);
    }
}
