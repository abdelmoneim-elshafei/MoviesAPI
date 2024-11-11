package com.noob.moviesapi.config;

import com.noob.moviesapi.exceptions.JwtAuthenticationEntryPoint;
import com.noob.moviesapi.security.JwtFilter;
import com.noob.moviesapi.security.JwtTokenGeneration;
import com.noob.moviesapi.services.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final JwtTokenGeneration jwtTokenGeneration;
    private final JwtFilter jwtFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> {
                    r.requestMatchers("/signup").permitAll();
                    r.requestMatchers("/login").permitAll();
                    r.anyRequest().authenticated();
                })
                .sessionManagement(s-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    // use it when you mark filter as spring bean and add it to filter chain, so it'll be executed twice
    // this prevents filter from being executed twice
    @Bean
    FilterRegistrationBean<JwtFilter> jwtFilterRegistrationBean(JwtFilter jwtFilter) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>(jwtFilter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

}
