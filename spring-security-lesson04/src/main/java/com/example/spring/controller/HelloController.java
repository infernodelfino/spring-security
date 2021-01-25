package com.example.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    public int a() {
        if (1 == 1) {
            throw new NullPointerException();
        }

        throw new NullPointerException();
    }
}
