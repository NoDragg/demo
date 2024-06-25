package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "Hello, World!";
    }
    @RequestMapping("/about")
    public String about() {
        return "This is the about page!";
    }
}
