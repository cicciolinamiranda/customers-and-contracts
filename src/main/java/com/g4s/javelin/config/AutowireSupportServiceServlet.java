package com.g4s.javelin.config;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.api.server.spi.SystemServiceServlet;

public class AutowireSupportServiceServlet extends SystemServiceServlet {

    @Override
    protected <T> T createService(final Class<T> arg0) {
        T service = super.createService(arg0);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(service);
        return service;
    }
}
