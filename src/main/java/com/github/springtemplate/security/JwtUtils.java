package com.github.springtemplate.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final SecretKey secretKey;
    public static final String HEADER_NAME = HttpHeaders.AUTHORIZATION;

    public boolean validateJwtToken(String jwt) {
        try {
            parseClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims parseClaims(String jwt) {
        return Jwts.parserBuilder().setSigningKey(secretKey)
    }
}
