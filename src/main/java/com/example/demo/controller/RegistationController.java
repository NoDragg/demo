package com.example.demo.controller;

import com.example.demo.model.MyUser;
import com.example.demo.repo.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegistationController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    MyUserRepo repo;
    @PostMapping("/register/user")
    public ResponseEntity<Object> register(@RequestBody MyUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(repo.save(user));
    }
}
