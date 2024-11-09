package com.noob.moviesapi.security;

import org.springframework.security.core.Authentication;

public interface JwtTokenGeneration {
    public String generateToken(Authentication authentication);
    public String getUsernameFromToken(String token);
    public boolean validateToken(String token);
}

