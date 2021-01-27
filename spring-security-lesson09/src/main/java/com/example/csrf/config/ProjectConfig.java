package com.example.csrf.config;

import com.example.csrf.security.filters.CustomCsrfTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf(csrfConfigurer -> {
            csrfConfigurer.ignoringAntMatchers("/csrfdisabled/**");

//            custom csrf repository
//            csrfConfigurer.csrfTokenRepository(new CustomTokenRepository());
        });
        http.addFilterAfter(new CustomCsrfTokenFilter(), CsrfFilter.class);
    }
}
