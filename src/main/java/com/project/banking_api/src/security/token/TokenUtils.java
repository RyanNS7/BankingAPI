package com.project.banking_api.src.security.token;

import com.project.banking_api.src.security.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenUtils{

    private final int EXPIRES = 3600000;

    @Value("${SECRET_KEY_JWT}")
    private String SECRET_KEY;


    public String generateToken(UserDetailsImpl user){

        String jwt = Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRES))
                .subject(user.getID().toString())
                .claim("email", user.getUsername())
                .signWith(getSignKey())
                .compact();

        return jwt;
    }

    public Claims extractClaims(String tokenJWT){

        String token = tokenJWT.replace("Bearer ", "");

        Claims decoded = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return decoded;
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

}
