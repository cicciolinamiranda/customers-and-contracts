package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.location.CustomerModel;
import com.g4s.javelin.data.repository.location.CustomerRepository;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.CustomerService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    @Lazy
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    public CustomerServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public CustomerDTO getCustomerByCustomerNumber(final String customerNumber) {
        CustomerModel result = customerRepository.findByCustomerNumber(customerNumber);
        return transformCustomer(result);
    }

    @Override
    public List<CustomerDTO> getCustomerByCustomerName(final String customerName) {
        List<CustomerModel> results = customerRepository.findByCustomerName(customerName);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> getCustomerByCustomerCode(final String customerCode) {
        List<CustomerModel> results = customerRepository.findByCustomerCode(customerCode);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> getCustomerByManualCustomerCode(final String manualCustomerCode) {
        List<CustomerModel> results = customerRepository.findByManualCustomerCode(manualCustomerCode);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> getCustomerByVatNumber(final String vatNumber) {
        List<CustomerModel> results = customerRepository.findByVatNumber(vatNumber);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> getCustomerByDunsNumber(final String dunsNumber) {
        List<CustomerModel> results = customerRepository.findByDunsNumber(dunsNumber);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> searchAllCustomers(final String searchTerm) {
        Long id = null;
        if (NumberUtils.isDigits(searchTerm)) {
            id = Long.valueOf(searchTerm);
        }
        String likeSearchTerm = "%" + searchTerm + "%";
        List<CustomerModel> results = customerRepository.searchCustomer(id, likeSearchTerm);
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    @Override
    public List<CustomerDTO> getCustomerList() {
        List<CustomerModel> results = customerRepository.findAll();
        List<CustomerDTO> list = Lists.newArrayList();
        for (CustomerModel result : results) {
            list.add(transformCustomer(result));
        }
        return list;
    }

    private CustomerDTO transformCustomer(final CustomerModel model) {
        CustomerDTO dto;
        dto = modelMapper.map(model, CustomerDTO.class);
//        dto.setCustomerLocation(transformCustomerLocation(model.getCustomerLocation()));
//        dto.setContract(transformContract(model.getContract()));
//        dto.setContacts(transformCustomerContact(model.getContacts()));
        return dto;
    }

}
