package com.g4s.javelin.data.repository.masterfile;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.masterfile.ModeTransportModel;

public interface ModeTransportRepository extends PagingAndSortingRepository<ModeTransportModel, Long> {

    List<ModeTransportModel> findByNameContainingIgnoreCase(final String searchTerm);
}
