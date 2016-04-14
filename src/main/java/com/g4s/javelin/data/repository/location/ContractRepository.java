package com.g4s.javelin.data.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.ContractModel;

/**
 * Created by apadilla on 4/8/16.
 */
public interface ContractRepository extends JpaRepository<ContractModel, Long> {

}
