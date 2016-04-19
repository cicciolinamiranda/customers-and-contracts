package com.g4s.javelin.api;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.CustomerService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import java.util.List;

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
            name = "customer.get.byCustomerNumber",
            path = "customer/customerNumber",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CustomerDTO getCustomerByCustomerNumber(@Named("customerNumber") final String customerNumber) {
        return customerService.getCustomerByCustomerNumber(customerNumber);
    }

    /**
     * Retrieve a specific customer by customerName
     *
     * @param customerName Customer name
     * @return List of Customers     matching the given customer number
     */
    @ApiMethod(
            name = "customer.get.byCustomerName",
            path = "customer/customerName",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByCustomerName(@Named("customerName") final String customerName) {
        return customerService.getCustomerByCustomerName(customerName);
    }

    /**
     * Retrieve a specific customer by customerCode
     *
     * @param customerCode Customer code
     * @return List of Customers matching the given customer number
     */
    @ApiMethod(
            name = "customer.get.byCustomerCode",
            path = "customer/customerCode",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByCustomerCode(@Named("customerCode") final String customerCode) {
        return customerService.getCustomerByCustomerCode(customerCode);
    }

    /**
     * Retrieve a specific customer by manualCustomerCode
     *
     * @param manualCustomerCode Manual Customer code
     * @return List of Customers matching the given manual customer number
     */
    @ApiMethod(
            name = "customer.get.byManualCustomerCode",
            path = "customer/manualCustomerCode",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByManualCustomerCode(@Named("manualCustomerCode") final String manualCustomerCode) {
        return customerService.getCustomerByManualCustomerCode(manualCustomerCode);
    }

    /**
     * Retrieve a specific customer by manualCustomerCode
     *
     * @param vatNumber VAT number
     * @return List of Customers matching the given VAT number
     */
    @ApiMethod(
            name = "customer.get.byVatNumber",
            path = "customer/vatNumber",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByVatNumber(@Named("vatNumber") final String vatNumber) {
        return customerService.getCustomerByVatNumber(vatNumber);
    }

    /**
     * Retrieve a specific customer by manualCustomerCode
     *
     * @param dunsNumber DUNS number
     * @return List of Customers matching the given DUNS number
     */
    @ApiMethod(
            name = "customer.get.byDunsNumber",
            path = "customer/dunsNumber",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerByDunsNumber(@Named("dunsNumber") final String dunsNumber) {
        return customerService.getCustomerByDunsNumber(dunsNumber);
    }

    /**
     * Retrieve list of all customers
     *
     * @return List of all existing customers
     */
    @ApiMethod(
            name = "customer.list",
            path = "customer/list",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerDTO> getCustomerList() {
        return customerService.getCustomerList();
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
        return customerService.searchAllCustomers(searchTerm);
    }

}
