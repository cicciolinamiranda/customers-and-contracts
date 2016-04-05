package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.masterfile.SkillsModel;

public interface SkillsRepository extends PagingAndSortingRepository<SkillsModel, Long> {

    List<SkillsModel> findByNameContainingIgnoreCase(final String name);
}
