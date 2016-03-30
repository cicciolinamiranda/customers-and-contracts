package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;

public interface MasterFileService {

    List<EquipmentDTO> getAllEquipments();

    List<ModeTransportDTO> getAllModeTransport();

    List<SkillsDTO> getAllSkills();

    List<TaskDTO> getAllTasks();
}
