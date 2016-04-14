package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.CustomerService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class CustomerApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CUSTOMER_SERVICE)
    private CustomerService customerService;

    /**
     * Retrieve a specific customer by customerNumber
     *
     * @param customerNumber Customer number
     * @return Customer Customer matching the given customer number
     */
    @ApiMethod(
            name = "customer.get",
            path = "customer",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CustomerDTO getCustomerDetail(@Named("customerNumber") final String customerNumber) {
        return customerService.getCustomerDetail(customerNumber);
    }

    /**
     * Retrieve a specific customer by customerNumber
     *
     * @param customerName Customer name
     * @return Customer Customer matching the given customer number
     */
    @ApiMethod(
            name = "customer.getByName",
            path = "customer/getByName",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByCustomerName(@Named("customerName") final String customerName) {
        return customerService.getCustomerByCustomerName(customerName);
    }

    /**
     * Retrieve list of all customers
     *
     * @return List of all existing customers
     */
    @ApiMethod(
            name = "customer.list",
            path = "customer/all",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerDetailsList() {
        return customerService.getCustomerDetailsList();
    }


    /**
     * Search All Customers
     *
     * @param searchTerm keyword
     * @return List of all customers that has a name or number like the keyword
     */
    @ApiMethod(
            name = "customer.search",
            path = "customer/search",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> searchCustomer(@Named("searchTerm") final String searchTerm) {
        List<CustomerDTO> result = customerService.searchAllCustomers(searchTerm);
        return result;
    }

}
