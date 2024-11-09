package com.noob.moviesapi.services.auth;

import com.noob.moviesapi.dtos.SignupRequest;

public interface Signup {
    public String signup(SignupRequest signupRequest);
}
