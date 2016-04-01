package com.g4s.javelin.data.repository.mock;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.mock.IncidentLogMockModel;

public interface IncidentLogRepository extends JpaRepository<IncidentLogMockModel, Long> {

}
