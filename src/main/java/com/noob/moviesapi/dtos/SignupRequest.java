package com.noob.moviesapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupRequest {
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    private String email;
}
