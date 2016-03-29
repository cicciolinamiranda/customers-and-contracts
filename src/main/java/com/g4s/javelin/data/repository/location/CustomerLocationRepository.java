package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.CustomerLocationModel;

public interface CustomerLocationRepository extends
        JpaRepository<CustomerLocationModel, Long> {

    List<CustomerLocationModel> findByWorkOrders(final Long id);

    @Query("SELECT CL FROM CUSTOMER_LOCATION CL WHERE CL.address LIKE '%:address%'")
    List<CustomerLocationModel> getCustomerLocationByAddress(final String address);


    @Query("SELECT CL FROM CUSTOMER_LOCATION CL LEFT JOIN CUSTOMER CU ON CL.customer_id = CU.id WHERE CU.customerName = :customerName")
    List<CustomerLocationModel> getCustomerLocationByCustomerName(final String customerName);
}
