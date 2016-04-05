package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.masterfile.EquipmentModel;

public interface EquipmentRepository extends PagingAndSortingRepository<EquipmentModel, Long> {

    List<EquipmentModel> findByNameContainingIgnoreCase(final String searchTerm);

}
