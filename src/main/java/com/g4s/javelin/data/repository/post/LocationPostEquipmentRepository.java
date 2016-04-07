package com.g4s.javelin.data.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.post.LocationPostEquipmentModel;

public interface LocationPostEquipmentRepository extends JpaRepository<LocationPostEquipmentModel, Long> {

    List<LocationPostEquipmentModel> findByPostId(final Long id);
}
