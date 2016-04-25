package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.exception.CustomerLocationException;

public interface CustomerLocationService {

    CustomerLocationDTO getCustomerLocationDetails(Long customerLocationId);
    List<CustomerLocationDTO> getCustomerLocationDetailsList(Long workOrderId);
    CustomerLocationDTO saveCustomerLocationDetails(CustomerLocationDTO customerLocation) throws CustomerLocationException;
    List<CustomerLocationDTO> getCustomerLocationByAddress(final String address);
    List<CustomerLocationDTO> getCustomerLocationByCustomerName(final String customerName);
    void addExistingCustomerLocationToAWorkOrder(Long customerLocationId, Long workOrderId)throws CustomerLocationException;
    CustomerLocationDTO updateCustomerLocationStatus(Long id, String status, String reasonForChange, String ipAddress)
            throws CustomerLocationException;
    List<CustomerLocationDTO> searchAllCustomerLocations(String searchTerm);
}
