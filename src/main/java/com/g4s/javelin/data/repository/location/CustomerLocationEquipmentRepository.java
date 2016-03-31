package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.CustomerLocationEquipmentModel;

public interface CustomerLocationEquipmentRepository
    extends JpaRepository<CustomerLocationEquipmentModel, Long> {

    List<CustomerLocationEquipmentModel> findByCustomerLocationId(final Long id);
}

