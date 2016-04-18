package com.g4s.javelin.data.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.post.LocationPostAllowancesModel;

public interface LocationPostAllowancesRepository extends JpaRepository<LocationPostAllowancesModel, Long> {

    List<LocationPostAllowancesModel> findByPostId(final Long id);
}
