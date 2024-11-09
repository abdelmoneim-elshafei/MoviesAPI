package com.noob.moviesapi.controllers;

import com.noob.moviesapi.dtos.LoginRequest;
import com.noob.moviesapi.dtos.SignupRequest;
import com.noob.moviesapi.services.auth.LoginSignupSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginSignupController {
    private final LoginSignupSerivce loginSignupSerivce;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(loginSignupSerivce.signup(signupRequest), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginSignupSerivce.login(loginRequest), HttpStatus.OK);
    }
}
