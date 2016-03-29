package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.CustomerLocationModel;

public interface CustomerLocationRepository extends
        JpaRepository<CustomerLocationModel, Long> {

    List<CustomerLocationModel> findByWorkOrdersId(final Long id);

    List<CustomerLocationModel> findByAddressAddressLike(final String address);


    List<CustomerLocationModel> findByCustomerCustomerName(final String customerName);
}
