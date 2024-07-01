package com.example.demo.config;

import com.example.demo.service.JwtService;
import com.example.demo.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); // get the header
        if (authHeader == null || !authHeader.startsWith("Bearer ")) { // check if the header is null or does not start with Bearer
            filterChain.doFilter(request, response); // if so, pass the request and response to the next filter
            return;
        }
        String jwt = authHeader.substring(7); // get the token
        String username = jwtService.extractUsername(jwt); // extract the username from the token
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // if the username is not null
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // load the user by username
            if(userDetails != null && jwtService.validateToken(jwt)) { // if the user is not null and the token is valid
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response); // pass the request and response to the next filter
    }
}
