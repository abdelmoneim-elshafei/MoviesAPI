package com.noob.moviesapi.services.auth;

import com.noob.moviesapi.dtos.JwtResponse;
import com.noob.moviesapi.dtos.LoginRequest;
import com.noob.moviesapi.dtos.SignupRequest;
import com.noob.moviesapi.entities.auth.AppUser;
import com.noob.moviesapi.entities.auth.Authority;
import com.noob.moviesapi.entities.auth.Role;
import com.noob.moviesapi.exceptions.ResourceExistException;
import com.noob.moviesapi.repositories.AppUserRepo;
import com.noob.moviesapi.repositories.RoleRepo;
import com.noob.moviesapi.security.JwtTokenGeneration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginSignupSerivce implements Login, Signup {
    private final AuthenticationManager manager;
    private final PasswordEncoder encoder;
    private final AppUserRepo userRepo;
    private final RoleRepo roleRepo;
    private final JwtTokenGeneration jwtTokenGeneration;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        authentication.getName();
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//        SecurityContextHolder.setContext(context);
        return JwtResponse.builder()
                .token(jwtTokenGeneration.generateToken(authentication))
                .username(authentication.getName())
                .build();
    }

    @Override
    public String signup(SignupRequest signupRequest) {
        if (userRepo.findByEmail(signupRequest.getEmail()).isPresent()) {
            throw new ResourceExistException("User", "Email", signupRequest.getEmail());
        }

        if (userRepo.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new ResourceExistException("User", "Username", signupRequest.getUsername());
        }

        Role role = roleRepo.findByAuthority(Authority.ROLE_USER).get();
        AppUser user = AppUser.builder().
                name(signupRequest.getName()).
                username(signupRequest.getUsername()).
                password(encoder.encode(signupRequest.getPassword())).
                email(signupRequest.getEmail())
                .roles(Set.of(role))
                .build();
        userRepo.save(user);
        return "registered successfully";
    }
}
