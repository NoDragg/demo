package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/user")
    public String userHome() {
        return "user";
    }
    @GetMapping("/admin")
    public String adminHome() {
        return "admin";
    }
    @GetMapping("/login")
    public String login() {
        return "custom_login";
    }

}
