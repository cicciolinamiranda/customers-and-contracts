package com.g4s.javelin.service.shiftpattern;

import java.util.List;

import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDTO;
import com.g4s.javelin.dto.core.shiftpattern.ShiftPatternDetailDTO;

public interface ShiftPatternService {
    ShiftPatternDTO saveShiftPattern(ShiftPatternDTO shiftPatternDTO, ShiftPatternDetailDTO shiftPatternDetailDTO);
    List<ShiftPatternDTO> getAllShiftPatterns(Long id);
    List<ShiftPatternDetailDTO> getDetailByShiftPatternId(Long shiftPatternId);
}
