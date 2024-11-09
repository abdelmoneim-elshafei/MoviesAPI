package com.noob.moviesapi.services.auth;

import com.noob.moviesapi.dtos.LoginRequest;

public interface Login {
    public String login(LoginRequest loginRequest);
}
