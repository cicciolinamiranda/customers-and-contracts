package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.masterfile.TaskModel;

public interface TaskRepository extends PagingAndSortingRepository<TaskModel, Long> {

    List<TaskModel> findByNameContainingIgnoreCase(final String searchName);
}
