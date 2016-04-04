package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.MethodOfRecordingModel;

public interface MethodOfRecordingRepository extends JpaRepository<MethodOfRecordingModel, Long> {

    List<MethodOfRecordingModel> findByNameContainingIgnoreCase(final String name);

}

