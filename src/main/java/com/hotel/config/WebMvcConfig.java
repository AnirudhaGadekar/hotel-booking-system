package com.hotel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/**") // 1. Protect ALL API endpoints
                .excludePathPatterns(       // 2. But allow these to be public:
                    "/api/login",           // Login endpoint
                    "/api/website",         // To load Logo/Title on login page
                    "/api/website/update"   // (Optional: usually protected, remove if admin only)
                );
               
    }
}