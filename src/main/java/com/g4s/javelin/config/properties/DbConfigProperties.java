package com.g4s.javelin.config.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public abstract class DbConfigProperties {

    @Autowired
    private Environment env;

    public String getDriverClassName() {
        return env.getProperty("spring.datasource.driver-class-name");
    }

    public String getUrl() {
        return env.getProperty("spring.datasource.url");
    }

    public String getUsername() {
        return env.getProperty("spring.datasource.username");
    }

    public String getPassword() {
        return env.getProperty("spring.datasource.password");
    }

    protected abstract Environment getEnv();

}

