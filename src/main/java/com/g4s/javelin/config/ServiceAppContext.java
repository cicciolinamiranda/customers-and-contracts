package com.g4s.javelin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.location.MasterFileService;
import com.g4s.javelin.service.location.MasterfileAssociationService;
import com.g4s.javelin.service.location.SiteLocationService;
import com.g4s.javelin.service.location.impl.BarredEmployeeServiceImpl;
import com.g4s.javelin.service.location.impl.CustomerLocationServiceImpl;
import com.g4s.javelin.service.location.impl.MasterFileServiceImpl;
import com.g4s.javelin.service.location.impl.MasterfileAssociationServiceImpl;
import com.g4s.javelin.service.location.impl.SiteLocationServiceImpl;

@Configuration
@Lazy
public class ServiceAppContext {

    @Bean(name = ServiceConstants.MASTER_FILE_SERVICE)
    public MasterFileService getMasterFileService() {
        return new MasterFileServiceImpl();
    }

    @Bean(name = ServiceConstants.BARRED_EMPLOYEE_SERVICE)
    public BarredEmployeeService getBarredEmployeeService() {
        return new BarredEmployeeServiceImpl();
    }
    @Bean(name = ServiceConstants.CUSTOMER_LOCATION_SERVICE)
    public CustomerLocationService getCustomerLocationService() {
        return new CustomerLocationServiceImpl();
    }
    @Bean(name = ServiceConstants.MASTERFILE_ASSOC_SERVICE)
    public MasterfileAssociationService getMasterfileAssociationService() {
        return new MasterfileAssociationServiceImpl();
    }
    @Bean(name = ServiceConstants.SITE_LOCATION_SERVICE)
    public SiteLocationService getSiteLocationService() {
        return new SiteLocationServiceImpl();
    }
}
