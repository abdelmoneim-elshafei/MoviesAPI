package com.noob.moviesapi.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
}
