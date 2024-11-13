package com.noob.moviesapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@RequiredArgsConstructor
public class AddInterceptor implements WebMvcConfigurer {
    private final LoggingFilterInterceptor loggingFilterInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingFilterInterceptor);
    }
}
