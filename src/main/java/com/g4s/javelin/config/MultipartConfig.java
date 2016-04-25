package com.g4s.javelin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.g4s.javelin.constants.UploadConstants;

@Configuration
@Lazy
public class MultipartConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding(UploadConstants.DEFAULT_ENCODING);
        // 1MB
        resolver.setMaxInMemorySize(UploadConstants.MAX_IN_MEMORY_SIZE);
        // 20MB
        resolver.setMaxUploadSize(UploadConstants.MAX_UPLOAD_SIZE);
        return resolver;
    }
}
