package com.noob.moviesapi.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private String token;
    @Builder.Default
    private String type = "Bearer";
    private String username;
//    private String email;
}
