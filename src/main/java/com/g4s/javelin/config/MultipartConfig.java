package com.g4s.javelin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@Lazy
public class MultipartConfig {
    private final int maxInMemorySize = 1048576;
    private final int maxUploadSize = 20971520;

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        // 1MB
        resolver.setMaxInMemorySize(maxInMemorySize);
        // 20MB
        resolver.setMaxUploadSize(maxUploadSize);
        return resolver;
    }
}
