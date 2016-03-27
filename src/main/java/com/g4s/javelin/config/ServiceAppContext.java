package com.g4s.javelin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.service.location.MasterFileService;
import com.g4s.javelin.service.location.impl.MasterFileServiceImpl;

@Configuration
@Lazy
public class ServiceAppContext {

    @Bean(name = ServiceConstants.MASTER_FILE_SERVICE)
    public MasterFileService getMasterFileService() {
        return new MasterFileServiceImpl();
    }
}
