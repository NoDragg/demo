package com.example.demo.controller;

import com.example.demo.model.MyUser;
import com.example.demo.repo.MyUserRepo;
import com.example.demo.service.JwtService;
import com.example.demo.service.MyUserDetailsService;
import com.example.demo.webtoken.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @PostMapping("/register/user")
    public ResponseEntity<Object> register(@RequestBody MyUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(repo.save(user));
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.username()));
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
