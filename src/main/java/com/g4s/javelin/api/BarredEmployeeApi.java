package com.g4s.javelin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeListDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

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
public class BarredEmployeeApi {

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.BARRED_EMPLOYEE_SERVICE)
    private BarredEmployeeService barredEmployeeService;

    /**
     * Save list of employees barred from a specific customer location.
     *
     * @param barredEmployees  List of employees to be barred from a specific customer location
     * @param customerLocation Customer location where specific employees are to be barred from
     */
    @ApiMethod(
            name = "barred.employees.add",
            path = "barred-employees",
            httpMethod = ApiMethod.HttpMethod.POST)
    public void saveBarredEmployees(final BarredEmployeeListDTO barredEmployees) {
        barredEmployeeService.saveBarredEmployees(barredEmployees.getBarredEmployeeDTOList(), barredEmployees.getCustomerLocationId());
    }

    /**
     * Retrieve a list of employees barred from a specific customer location
     *
     * @param customerLocationId Customer location id
     * @return List of employees barred for the customer location matching the given id
     */
    @ApiMethod(
            name = "barred.employees.list",
            path = "barred-employees/all",
            httpMethod = ApiMethod.HttpMethod.GET)
    public List<BarredEmployeeDTO> getBarredEmployees(@Named("id") final Long customerLocationId) {
        return barredEmployeeService.getBarredEmployees(customerLocationId);
    }
}
