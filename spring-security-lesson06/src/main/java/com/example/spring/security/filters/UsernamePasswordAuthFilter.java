package com.example.spring.security.filters;

import com.example.spring.entities.Otp;
import com.example.spring.repository.OtpRepository;
import com.example.spring.security.authentications.OtpAuthentication;
import com.example.spring.security.authentications.UsernamePasswordAuthentication;
import com.example.spring.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1: username & password
        //2: username & otp

        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (otp == null) {
            // step 1
            Authentication usernameAndPassAuth = new UsernamePasswordAuthentication(username, password);
            usernameAndPassAuth = authenticationManager.authenticate(usernameAndPassAuth);

            // generate OTP
            String otpCode = String.valueOf(new Random().nextInt(9999) + 1000);

            Otp otpEntity = new Otp();
            otpEntity.setUsername(username);
            otpEntity.setOtp(otpCode);

            // save Otp in DB
            otpRepository.save(otpEntity);
        } else {
            // step 2
            Authentication otpAuth = new OtpAuthentication(username, otp);
            otpAuth = authenticationManager.authenticate(otpAuth);

            // issue a token
            String token = String.valueOf(UUID.randomUUID());
            tokenManager.add(token);
            response.setHeader("Authorization", token);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
