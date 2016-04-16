package com.g4s.javelin.config;


import com.g4s.javelin.service.location.*;
import com.g4s.javelin.service.location.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.service.masterfile.MasterfileService;
import com.g4s.javelin.service.masterfile.impl.MasterfileServiceImpl;
import com.g4s.javelin.service.post.PostMasterfileAssociationService;
import com.g4s.javelin.service.post.PostService;
import com.g4s.javelin.service.post.impl.PostMasterfileAssociationServiceImpl;
import com.g4s.javelin.service.post.impl.PostServiceImpl;


@Configuration
@Lazy
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

    @Bean(name = ServiceConstants.CUSTOMER_SERVICE)
    public CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

    @Bean (name = ServiceConstants.CONTACT_SERVICE)
    public ContactService getContactService() {
        return new ContactServiceImpl();
    }

    @Bean (name = ServiceConstants.CONTRACT_SERVICE)
    public ContractService getContractService() {
        return new ContractServiceImpl();
    }
}
