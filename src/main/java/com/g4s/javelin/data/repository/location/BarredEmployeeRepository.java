package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.BarredEmployeeModel;

public interface BarredEmployeeRepository extends JpaRepository<BarredEmployeeModel, Long> {

    @Query("SELECT BE FROM BARRED_EMPLOYEE BE WHERE BE.CUSTOMER_LOCATION_ID = :id")
    public List<BarredEmployeeModel> getBarredEmployeeByCustomerLocation(final Long id);
}
