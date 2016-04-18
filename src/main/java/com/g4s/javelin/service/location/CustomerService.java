package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.CustomerDTO;


public interface CustomerService {

    CustomerDTO getCustomerByCustomerNumber(final String customerNumber);
    List<CustomerDTO> getCustomerByCustomerName(final String customerName);
    List<CustomerDTO> getCustomerByCustomerCode(final String customerCode);
    List<CustomerDTO> getCustomerByManualCustomerCode(final String manualCustomerCode);
    List<CustomerDTO> getCustomerByVatNumber(final String vatNumber);
    List<CustomerDTO> getCustomerByDunsNumber(final String dunsNumber);
    List<CustomerDTO> searchAllCustomers(final String searchTerm);
    List<CustomerDTO> getCustomerList();
}
