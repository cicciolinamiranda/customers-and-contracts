package com.g4s.javelin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.aspect.AuditLogAspect;
import com.g4s.javelin.constants.ServiceConstants;

import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.ContractService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.location.CustomerService;
import com.g4s.javelin.service.location.LocationMasterfileAssociationService;
import com.g4s.javelin.service.location.SiteLocationService;

import com.g4s.javelin.service.location.impl.BarredEmployeeServiceImpl;
import com.g4s.javelin.service.location.impl.ContractServiceImpl;
import com.g4s.javelin.service.location.impl.CustomerLocationServiceImpl;
import com.g4s.javelin.service.location.impl.CustomerServiceImpl;
import com.g4s.javelin.service.location.impl.LocationMasterfileAssociationServiceImpl;
import com.g4s.javelin.service.location.impl.SiteLocationServiceImpl;

import com.g4s.javelin.service.masterfile.MasterfileService;
import com.g4s.javelin.service.masterfile.impl.MasterfileServiceImpl;
import com.g4s.javelin.service.post.PostMasterfileAssociationService;
import com.g4s.javelin.service.post.PostService;
import com.g4s.javelin.service.post.impl.PostMasterfileAssociationServiceImpl;
import com.g4s.javelin.service.post.impl.PostServiceImpl;


@Configuration
@Lazy
@EnableAspectJAutoProxy
public class ServiceAppContext {

    @Bean(name = ServiceConstants.MASTER_FILE_SERVICE)
    public MasterfileService getMasterFileService() {
        return new MasterfileServiceImpl();
    }

    @Bean(name = ServiceConstants.BARRED_EMPLOYEE_SERVICE)
    public BarredEmployeeService getBarredEmployeeService() {
        return new BarredEmployeeServiceImpl();
    }

    @Bean(name = ServiceConstants.CUSTOMER_LOCATION_SERVICE)
    public CustomerLocationService getCustomerLocationService() {
        return new CustomerLocationServiceImpl();
    }

    @Bean(name = ServiceConstants.LOCATION_MASTERFILE_ASSOC_SERVICE)
    public LocationMasterfileAssociationService getLocationMasterfileAssociationService() {
        return new LocationMasterfileAssociationServiceImpl();
    }

    @Bean(name = ServiceConstants.SITE_LOCATION_SERVICE)
    public SiteLocationService getSiteLocationService() {
        return new SiteLocationServiceImpl();
    }

    @Bean(name = ServiceConstants.POST_SERVICE)
    public PostService getPostService() {
        return new PostServiceImpl();
    }

    @Bean(name = ServiceConstants.POST_MASTERFILE_ASSOC_SERVICE)
    public PostMasterfileAssociationService getPostMasterfileAssociationService() {
        return new PostMasterfileAssociationServiceImpl();
    }

    @Bean(name = ServiceConstants.CONTRACT_SERVICE)
    public ContractService getContractService() {
        return new ContractServiceImpl();
    }

    @Bean(name = ServiceConstants.CUSTOMER_SERVICE)
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

    @Bean
    public AuditLogAspect getAuditLogAspect() {
        return new AuditLogAspect();
    }
}
