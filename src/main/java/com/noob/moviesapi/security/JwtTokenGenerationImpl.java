package com.noob.moviesapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerationImpl implements JwtTokenGeneration {

    private final SecretKey secret = new SecretKeySpec("aaji48jfja3ijfaaooll8$1113###188341ji48jfja3ijfaaooll8$1113###188341".getBytes(), "HmacSHA512");
    private final int expiration = 3600000;

    @Override
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expiration);

        return Jwts.builder().claims()
                .subject(username)
                .issuer("movies-api")
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .and()
                .signWith(secret)
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secret).build().parseSignedClaims(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
