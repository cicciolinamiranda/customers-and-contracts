package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.joda.time.format.DateTimeFormat;
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

    @Override
    public void saveBarredEmployees(final List<BarredEmployeeDTO> employees, final Long customerLocationId) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        List<BarredEmployeeModel> barredEmployees = Lists.newArrayList();
        CustomerLocationModel location = customerLocationRepository.findOne(customerLocationId);
        BarredEmployeeModel model;
        if (!CollectionUtils.isEmpty(employees)) {
            for (BarredEmployeeDTO dto : employees) {
                if (dto.isDeleted()) {
                    barredEmployeeRepository.delete(dto.getId());
                } else {
                    model = new BarredEmployeeModel();
                    if (dto.getId() != null) {
                        model.setId(dto.getId());
                    }
                    model.setEmployeeId(dto.getEmployeeId());
                    model.setStartDate(dto.getStartDate());
                    model.setEndDate(dto.getEndDate());
                    model.setFirstName(dto.getFirstName());
                    model.setLastName(dto.getLastName());
                    model.setTitle(dto.getTitle());
                    model.setCustomerLocation(location);
                    if (dto.getStartDateStr() != null) {
                        model.setStartDate(dtf.parseDateTime(dto.getStartDateStr()));
                    }
                    barredEmployees.add(model);
                }
            }
        }
        barredEmployeeRepository.save(barredEmployees);
    }

    @Override
    public List<BarredEmployeeDTO> getBarredEmployees(final Long customerLocationId) {
        List<BarredEmployeeModel> employees = barredEmployeeRepository.findByCustomerLocationId(customerLocationId);
        List<BarredEmployeeDTO> barredEmployees = Lists.newArrayList();
        BarredEmployeeDTO dto;
        for (BarredEmployeeModel emp : employees) {
            dto = new BarredEmployeeDTO();
            dto.setEmployeeId(emp.getEmployeeId());
            dto.setEndDate(emp.getEndDate());
            dto.setStartDate(emp.getStartDate());
            dto.setFirstName(emp.getFirstName());
            dto.setLastName(emp.getLastName());
            dto.setId(emp.getId());
            barredEmployees.add(dto);
        }
        return barredEmployees;
    }

}
