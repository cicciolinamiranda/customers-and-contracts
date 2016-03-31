package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.location.TaskModel;

public interface TaskRepository extends PagingAndSortingRepository<TaskModel, Long> {

    List<TaskModel> findByTaskNameContainingIgnoreCase(final String searchName);
}
