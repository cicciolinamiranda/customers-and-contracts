package com.g4s.javelin.data.repository.shiftpattern;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g4s.javelin.data.model.shiftpattern.ShiftPatternDetailModel;

public interface ShiftPatternDetailRepository extends JpaRepository<ShiftPatternDetailModel, Long> {
    List<ShiftPatternDetailModel> findByShiftPatternId(final Long shiftPatternId);

}
