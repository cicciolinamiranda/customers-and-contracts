package com.g4s.javelin.data.repository.location;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.location.TaskModel;

public interface TaskRepository extends PagingAndSortingRepository<TaskModel, Long> {

}
