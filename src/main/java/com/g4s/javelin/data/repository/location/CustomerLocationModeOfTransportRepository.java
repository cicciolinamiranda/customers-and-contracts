package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.CustomerLocationModeOfTransportModel;

public interface CustomerLocationModeOfTransportRepository
    extends JpaRepository<CustomerLocationModeOfTransportModel, Long> {

    List<CustomerLocationModeOfTransportModel> findByCustomerLocationId(final Long id);

}
