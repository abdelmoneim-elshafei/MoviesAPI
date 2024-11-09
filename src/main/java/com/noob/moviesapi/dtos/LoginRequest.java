package com.noob.moviesapi.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
