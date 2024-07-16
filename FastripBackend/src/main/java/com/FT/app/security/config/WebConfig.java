package com.FT.app.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        		.allowedOrigins("http://localhost:9200")
        		//만료
                //.allowedOrigins("http://fastrip.shop")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}