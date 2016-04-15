package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    List<CustomerModel> findByCustomerNumber(final String customerNumber);
    List<CustomerModel> findByCustomerCode(final String customerCode);
    List<CustomerModel> findByCustomerName(final String customerName);
    List<CustomerModel> findByManualCustomerCode(final String manualCustomerCode);
    List<CustomerModel> findByVatNumber(final String vatNumber);
    List<CustomerModel> findByDunsNumber(final String dunsNumber);
    List<CustomerModel> findAll();

    @Query("SELECT CM FROM CustomerModel CM where CM.id = ?1 OR CM.customerName LIKE %?2%"
            + "OR CM.customerNumber LIKE %?2% OR CM.customerCode LIKE %?2% OR CM.vatNumber LIKE %?2%"
            + "OR CM.manualCustomerCode LIKE %?2% OR CM.dunsNumber LIKE %?2%"
    )
    List<CustomerModel> findBySearchTerm(Long id, String searchTerm);

}
