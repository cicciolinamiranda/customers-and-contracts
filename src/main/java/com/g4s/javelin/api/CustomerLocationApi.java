package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.enums.SearchCriteriaEnum;
import com.g4s.javelin.exception.CustomerLocationException;
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
        description = ApiConstants.API_DESCRIPTION)
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
            name = "workorder.customer.location.get",
            path = "workorder/customer-location",
            httpMethod = ApiMethod.HttpMethod.GET)
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
            name = "workorder.customer.location.list",
            path = "workorder/customer-location/all",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<CustomerLocationDTO> getCustomerLocationDetailsList(@Named("id") final Long workOrderId) {
        return customerLocationService.getCustomerLocationDetailsList(workOrderId);
    }

    /**
     * Save existing customer location details to an existing work order
     *
     * @param customerLocationId Customer Location id
     * @param workOrderId Work order id
     */
    @ApiMethod(
            name = "workorder/customer.location.add_existing",
            path = "workorder/customer-location/add_existing",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void addExistingCustomerLocationToAWorkOrder(@Named("customerLocationId") final Long customerLocationId,
            @Named("workOrderId") final Long workOrderId) throws CustomerLocationException {
        customerLocationService.addExistingCustomerLocationToAWorkOrder(customerLocationId, workOrderId);
    }

    /**
     * Save customer location details
     *
     * @param customerLocationDTO Customer location details
     */
    @ApiMethod(
            name = "workorder.customer.location.save",
            path = "workorder/customer-location/save",
            httpMethod = ApiMethod.HttpMethod.POST)
    public CustomerLocationDTO saveCustomerLocationDetails(final CustomerLocationDTO customerLocationDTO)
            throws CustomerLocationException {
        CustomerLocationDTO response = customerLocationService.saveCustomerLocationDetails(customerLocationDTO);
        return response;
    }

    /**
     * Update customer location details
     *
     * @param customerLocationDTO Customer location details
     */
    @ApiMethod(
            name = "workorder.customer.location.update",
            path = "workorder/customer-location/update",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void updateCustomerLocationDetails(final CustomerLocationDTO customerLocationDTO)
            throws CustomerLocationException {
        customerLocationService.saveCustomerLocationDetails(customerLocationDTO);
    }

    /**
     * Seach customer location details by filter
     *
     * @param criteria Search Criteria (Customer Name, Address, Id)
     * @param value search value
     */
    @ApiMethod(
            name = "workorder.customer.location.search.filter",
            path = "workorder/customer-location/search-filter",
            httpMethod = ApiMethod.HttpMethod.POST)
    public List<CustomerLocationDTO> searchCustomerLocationByFilter(@Named("criteria") final String criteria,
            @Named("value") final String value) {
        List<CustomerLocationDTO> list = Lists.newArrayList();
        SearchCriteriaEnum sCriteria = SearchCriteriaEnum.findByCode(criteria);
        if (SearchCriteriaEnum.ADDRESS.equals(sCriteria)) {
            list = customerLocationService.getCustomerLocationByAddress(value);
        } else if (SearchCriteriaEnum.CUSTOMER.equals(sCriteria)) {
            list = customerLocationService.getCustomerLocationByCustomerName(value);
        } else if (SearchCriteriaEnum.ID.equals(sCriteria)) {
            list.add(customerLocationService.getCustomerLocationDetails(Long.valueOf(value)));
        }
        return list;
    }

    /**
     * Update customer location status
     *
     * @param id Customer Location id
     * @param status Status
     */
    @ApiMethod(
            name = "workorder.customer.location.update_status",
            path = "workorder/customer-location/update-status",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void updateCustomerLocationStatus(@Named("id") final Long id,
            @Named("status") final String status) {
        customerLocationService.updateCustomerLocationStatus(id, status);
    }

    /**
     * Search All Location
     *
     *
     */
    @ApiMethod(
            name = "workorder.customer.location.search",
            path = "workorder/customer-location/search",
            httpMethod = ApiMethod.HttpMethod.POST)
    public List<CustomerLocationDTO> searchCustomerLocations(@Named("searchterm") final String searchTerm) {
        List<CustomerLocationDTO> result = customerLocationService.searchAllCustomerLocations(searchTerm);
        return result;
    }

}
