package com.example.springsecurityregistrationloginMySQLthymeleaf.controller;

import com.example.springsecurityregistrationloginMySQLthymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

//    @PostMapping("/login")
//    public String home(@RequestParam("email") String email) {
//        userService.loadUserByUsername(email);
//        return "index";
//    }
}
