package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.CustomerLocationModel;

public interface CustomerLocationRepository extends JpaRepository<CustomerLocationModel, Long>{

    List<CustomerLocationModel> findByWorkOrders(Long id);
}
