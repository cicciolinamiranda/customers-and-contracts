package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;

public interface BarredEmployeeService {

    void saveBarredEmployees(List<BarredEmployeeDTO> employees, Long customerLocationId);

    List<BarredEmployeeDTO> getBarredEmployees(Long customerLocationId);
}
