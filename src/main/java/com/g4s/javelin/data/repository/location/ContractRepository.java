package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.g4s.javelin.data.model.location.ContractModel;

/**
 * Created by apadilla on 4/8/16.
 */
public interface ContractRepository extends JpaRepository<ContractModel, Long> {

    List<ContractModel> findAll();

    List<ContractModel> findByCustomerId(final Long id);

    List<ContractModel> findByNumber(final String number);

    List<ContractModel> findByName(final String name);

    @Query("SELECT CM FROM ContractModel CM where CM.id = ?1 OR CM.number LIKE %?2% "
            + "OR CM.name LIKE %?2% OR CM.title LIKE %?2%")
    List<ContractModel> findBySearchTerm(Long id, String customerName);


}
