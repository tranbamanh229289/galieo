package com.manhtb.authservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    Logger logger = LoggerFactory.getLogger(CorsConfig.class);
    @Value("${cors.pattern}")
    private String pattern;

    @Value("${cors.methods}")
    private String methods;

    @Value("${cors.origins}")
    private String origins;

    @Override
    public void addCorsMappings(
            CorsRegistry registry

    ) {
        registry.addMapping(pattern).allowedMethods(methods).allowedOrigins(origins);
    }

}
