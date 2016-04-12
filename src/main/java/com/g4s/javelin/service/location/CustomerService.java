package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.CustomerDTO;


public interface CustomerService {

    CustomerDTO getCustomerDetail(final String customerNumber);
    List<CustomerDTO> getCustomerDetailsList();
    List<CustomerDTO> getCustomerByCustomerName(final String customerName);
    List<CustomerDTO> searchAllCustomers(final String searchTerm);
}
