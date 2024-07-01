package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
@Service
public class JwtService {
    private final String SECRET_KEY = "BC5BDCEBDED8093C0755C983EC4E47F482C5AEFB0412943C152F53D2C865CDC3";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(60))))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
      byte[] decodedKey =  Base64.getDecoder().decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(decodedKey);
    }
    public String extractUsername(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        Claims claims = Jwts.parser()
                 .verifyWith(generateKey())
                 .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }

    public boolean validateToken(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
