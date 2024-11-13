package com.noob.moviesapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@NoArgsConstructor
//@Component
public class FilterLoggingFilter extends OncePerRequestFilter {
    private FilterChainProxy filterChainProxy;
//    @Autowired
    private final ApplicationContext applicationContext;

    public FilterLoggingFilter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if(filterChainProxy == null) {
            filterChainProxy = applicationContext.getBean(FilterChainProxy.class);
        }
        System.out.println("Security Filters in FilterChain for request: " + request.getRequestURI());
        filterChainProxy.getFilterChains().forEach(securityFilterChain ->
                securityFilterChain.getFilters().forEach(filter ->
                        System.out.println(" - " + filter.getClass().getName())
                )
        );
        // Continue with the rest of the filter chain
        chain.doFilter(request, response);
    }

}


