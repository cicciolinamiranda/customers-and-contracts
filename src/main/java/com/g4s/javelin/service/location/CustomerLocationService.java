package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.exception.CustomerLocationException;

public interface CustomerLocationService {

    CustomerLocationDTO getCustomerLocationDetails (Long customerLocationId);
    List<CustomerLocationDTO> getCustomerLocationDetailsList (Long workOrderId);
    void saveCustomerLocationDetails(CustomerLocationDTO customerLocation);
    List<CustomerLocationDTO> getCustomerLocationByAddress(String address);
    List<CustomerLocationDTO> getCustomerLocationByCustomerName(String customerName);
    void addExistingCustomerLocationToAWorkOrder (Long customerLocationId, Long workOrderId)throws CustomerLocationException;
    CustomerLocationDTO createCustomerLocation();
    void updateCustomerLocationStatus(Long id, String status);
}
