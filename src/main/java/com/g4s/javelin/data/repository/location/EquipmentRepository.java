package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.location.EquipmentModel;

public interface EquipmentRepository extends PagingAndSortingRepository<EquipmentModel, Long> {

    List<EquipmentModel> findByEquipmentNameContainingIgnoreCase(final String searchTerm);

}
