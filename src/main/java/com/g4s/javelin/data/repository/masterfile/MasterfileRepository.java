package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.enums.MasterfileTypeEnum;

public interface MasterfileRepository extends JpaRepository<MasterfileModel, Long> {

    List<MasterfileModel> findByType(final MasterfileTypeEnum type);
    List<MasterfileModel> findByTypeAndNameContainingIgnoreCase(final MasterfileTypeEnum type, final String name);
}
