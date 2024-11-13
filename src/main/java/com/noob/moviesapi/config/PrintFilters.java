package com.noob.moviesapi.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;

//@Configuration
@RequiredArgsConstructor
public class PrintFilters {

    private final FilterChainProxy filterChainProxy;
    @PostConstruct
    public void printSecurityFilters() {
        System.out.println("Security Filters in FilterChain:");
        filterChainProxy.getFilterChains().forEach(chain ->
                chain.getFilters().forEach(filter -> System.out.println(filter.getClass().getName()))
        );
    }}
