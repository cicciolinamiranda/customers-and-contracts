package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.location.SkillsModel;

public interface SkillsRepository extends PagingAndSortingRepository<SkillsModel, Long> {

    List<SkillsModel> findBySkillNameContainingIgnoreCase(final String name);
}
