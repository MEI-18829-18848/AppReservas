package com.appreservas.auth.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home(UserDetails principal){
        return "Hello, "+ principal.getUsername();
    }
}
