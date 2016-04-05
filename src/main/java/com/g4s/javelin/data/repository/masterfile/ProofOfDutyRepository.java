package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.masterfile.ProofOfDutyModel;

public interface ProofOfDutyRepository extends JpaRepository<ProofOfDutyModel, Long> {

    List<ProofOfDutyModel> findByNameContainingIgnoreCase(final String name);

}
