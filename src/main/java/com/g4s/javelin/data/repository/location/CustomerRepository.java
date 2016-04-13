package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    CustomerModel findByCustomerNumber(final String customerNumber);

    List<CustomerModel> findAll();

    List<CustomerModel> findByCustomerName(final String customerName);

    @Query("SELECT CM FROM CustomerModel CM where CM.id = ?1 OR CM.customerName LIKE %?2%")
    List<CustomerModel> findBySearchTerm(Long id, String customerName);

}
