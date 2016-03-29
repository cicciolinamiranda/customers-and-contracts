package com.g4s.javelin.api.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

/**
 * @author Jordan Duabe
 * @since 03/28/2016
 */
@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION
)
public class CustomerLocationApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.CUSTOMER_LOCATION_SERVICE)
    private CustomerLocationService customerLocationService;

    /**
     * Retrieve a specific customer location
     *
     * @param customerLocationId Customer location id
     * @return Customer location matching the given id
     */
    @ApiMethod(
            name = "customer.location.get",
            path = "customer-location",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public CustomerLocationDTO getCustomerLocationDetails(@Named("id") final Long customerLocationId) {
        return customerLocationService.getCustomerLocationDetails(customerLocationId);
    }

    /**
     * Retrieve list of customer locations bound to a specific work order
     *
     * @param workOrderId Work order id
     * @return List of customer locations that belong to the work order matching the given id
     */
    @ApiMethod(
            name = "customer.location.list",
            path = "customer-location/all",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<CustomerLocationDTO> getCustomerLocationDetailsList(@Named("id") final Long workOrderId) {
        return customerLocationService.getCustomerLocationDetailsList(workOrderId);
    }

    /**
     * Save customer location details
     *
     * @param customerLocationDTO Customer location details
     */
    @ApiMethod(
            name = "customer.location.add",
            path = "customer-location",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public void saveCustomerLocationDetails(final CustomerLocationDTO customerLocationDTO) {
        customerLocationService.saveCustomerLocationDetails(customerLocationDTO);
    }
    
    /**
     * Seach customer location details
     *
     * @param customerLocationDTO Customer location details
     */
    @ApiMethod(
            name = "customer.location.searcg",
            path = "customer-location/search",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public List<CustomerLocationDTO> searchCustomerLocation(@Named("customerId") final Long customerId,
            @Named("criteria") final String criteria, @Named("value") final String value) {
        List<CustomerLocationDTO> list = Lists.newArrayList();
        
        if ("ADDRESS".equalsIgnoreCase(criteria)) {
           list = customerLocationService.getCustomerLocationByAddress(value);
        } else if ("CUSTOMER".equalsIgnoreCase(criteria)) {
        	list = customerLocationService.getCustomerLocationByCustomerName(value);
        } else if ("ID".equalsIgnoreCase(criteria)) {
        	list.add(customerLocationService.getCustomerLocationDetails(Long.valueOf(value)));
        }
        return list;
    }
}
