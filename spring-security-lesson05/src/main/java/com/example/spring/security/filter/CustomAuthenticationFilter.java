package com.example.spring.security.filter;

import com.example.spring.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter implements Filter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        String authorization = servletRequest.getHeader("Authorization");

        CustomAuthentication customAuthentication = new CustomAuthentication(authorization, null);

        try {

            Authentication authenticationResult = authenticationManager.authenticate(customAuthentication);

            if (authenticationResult.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticationResult);
                chain.doFilter(request, response);
            } else {
                servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        } catch (AuthenticationException e) {
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

}
