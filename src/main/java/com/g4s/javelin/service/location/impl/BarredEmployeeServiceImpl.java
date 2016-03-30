package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.location.BarredEmployeeModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.repository.location.BarredEmployeeRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class BarredEmployeeServiceImpl implements BarredEmployeeService {

    @Autowired
    @Lazy
    private BarredEmployeeRepository barredEmployeeRepository;

    @Autowired
    @Lazy
    private CustomerLocationRepository customerLocationRepository;

    public void saveBarredEmployees(final List<BarredEmployeeDTO> employees, final Long customerLocationId) {
        List<BarredEmployeeModel> barredEmployees = Lists.newArrayList();
        CustomerLocationModel location = customerLocationRepository.findOne(customerLocationId);
        BarredEmployeeModel model;
        if (!CollectionUtils.isEmpty(employees)) {
            for (BarredEmployeeDTO dto : employees) {
                model = new BarredEmployeeModel();
                if (dto.getId() != null) {
                    model.setId(dto.getId());
                }
                model.setEmployeeId(dto.getEmployeeId());
                model.setStartDate(dto.getStartDate());
                model.setEndDate(dto.getEndDate());
                model.setCustomerLocation(location);
                barredEmployees.add(model);
            }
        }
        barredEmployeeRepository.save(barredEmployees);
    }

    public List<BarredEmployeeDTO> getBarredEmployees(final Long customerLocationId) {
        List<BarredEmployeeModel> employees = barredEmployeeRepository.findByCustomerLocationId(customerLocationId);
        List<BarredEmployeeDTO> barredEmployees = Lists.newArrayList();
        BarredEmployeeDTO dto;
        for (BarredEmployeeModel emp : employees) {
            dto = new BarredEmployeeDTO();
            dto.setEmployeeId(emp.getEmployeeId());
            dto.setEndDate(emp.getEndDate());
            dto.setStartDate(emp.getStartDate());
            //connect to mock to get employee first name and last name
            barredEmployees.add(dto);
        }
        return barredEmployees;
    }

}
