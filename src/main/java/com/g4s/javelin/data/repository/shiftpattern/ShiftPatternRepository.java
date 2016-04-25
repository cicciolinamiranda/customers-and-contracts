package com.g4s.javelin.data.repository.shiftpattern;

import org.springframework.data.jpa.repository.JpaRepository;
import com.g4s.javelin.data.model.shiftpattern.ShiftPatternModel;

public interface ShiftPatternRepository extends JpaRepository<ShiftPatternModel, Long> {

}
