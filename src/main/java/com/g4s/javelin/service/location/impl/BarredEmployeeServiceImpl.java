package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.location.BarredEmployeeModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.repository.location.BarredEmployeeRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class BarredEmployeeServiceImpl implements BarredEmployeeService {

    @Autowired
    @Lazy
    private BarredEmployeeRepository barredEmployeeRepository;

    public void saveBarredEmployees(List<BarredEmployeeDTO> employees, CustomerLocationModel custLocation) {
        List<BarredEmployeeModel> barredEmployees = Lists.newArrayList();
        BarredEmployeeModel model;
        for (BarredEmployeeDTO dto : employees) {
            model = new BarredEmployeeModel();
            if (dto.getId() != null) {
                model.setId(dto.getId());
            }
            model.setEmployeeId(dto.getEmployeeId());
            model.setStartDate(dto.getStartDate());
            model.setEndDate(dto.getEndDate());
            model.setCustomerLocation(custLocation);
            barredEmployees.add(model);
        }
        barredEmployeeRepository.save(barredEmployees);
    }

    public List<BarredEmployeeDTO> getBarredEmployees(Long customerLocationId) {
        List<BarredEmployeeModel> employees = barredEmployeeRepository.getBarredEmployeeByCustomerLocation(customerLocationId);
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
