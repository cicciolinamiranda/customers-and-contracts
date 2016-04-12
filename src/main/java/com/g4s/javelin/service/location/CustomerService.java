package com.g4s.javelin.service.location;

import com.g4s.javelin.dto.core.location.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO getCustomerDetails(final Long customerId);
    List<CustomerDTO> getCustomerDetailsList();
    List<CustomerDTO> getCustomerByCustomerName(final String customerName);
    List<CustomerDTO> searchAllCustomers(final String searchTerm);
}
