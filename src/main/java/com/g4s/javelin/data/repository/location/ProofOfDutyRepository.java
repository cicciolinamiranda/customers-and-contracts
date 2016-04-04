package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.location.ProofOfDutyModel;

public interface ProofOfDutyRepository extends JpaRepository<ProofOfDutyModel, Long> {

    List<ProofOfDutyModel> findByNameContainingIgnoreCase(final String name);

}
