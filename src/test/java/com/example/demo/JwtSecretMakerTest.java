package com.example.demo;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class JwtSecretMakerTest {
    @Test
    public void generateSecretKey(){
      SecretKey key = Jwts.SIG.HS256.key().build();
      String keyEncoded  = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(keyEncoded);
    }
}
