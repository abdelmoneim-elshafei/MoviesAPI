package com.noob.moviesapi.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class LoggingFilterInterceptor implements HandlerInterceptor {
    @Autowired
    private final FilterChainProxy filterChainProxy;
    public LoggingFilterInterceptor(FilterChainProxy filterChainProxy) {
        this.filterChainProxy = filterChainProxy;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Security Filters in FilterChain for request: " + request.getRequestURI());
        filterChainProxy.getFilterChains().forEach(securityFilterChain ->
                securityFilterChain.getFilters().forEach(filter ->
                        System.out.println(" - " + filter.getClass().getName())
                )
        );
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Security Filters in FilterChain for request: " + request.getRequestURI());
        filterChainProxy.getFilterChains().forEach(securityFilterChain ->
                securityFilterChain.getFilters().forEach(filter ->
                        System.out.println(" - " + filter.getClass().getName())
                )
        );
    }
}
