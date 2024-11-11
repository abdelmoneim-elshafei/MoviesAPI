package com.noob.moviesapi.services.auth;

import com.noob.moviesapi.dtos.JwtResponse;
import com.noob.moviesapi.dtos.LoginRequest;

public interface Login {
    public JwtResponse login(LoginRequest loginRequest);
}
