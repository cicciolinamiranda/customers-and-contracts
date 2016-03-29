package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.BarredEmployeeModel;

public interface BarredEmployeeRepository extends JpaRepository<BarredEmployeeModel, Long> {

    public List<BarredEmployeeModel> findByCustomerLocationId(final Long id);
}
