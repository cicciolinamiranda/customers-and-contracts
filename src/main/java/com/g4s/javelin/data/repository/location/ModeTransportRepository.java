package com.g4s.javelin.data.repository.location;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.g4s.javelin.data.model.location.ModeTransportModel;

public interface ModeTransportRepository extends PagingAndSortingRepository<ModeTransportModel, Long> {

    List<ModeTransportModel> findByTransportNameContainingIgnoreCase(final String searchTerm);
}
