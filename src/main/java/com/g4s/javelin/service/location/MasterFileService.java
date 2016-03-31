package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.MasterFileDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;

public interface MasterFileService {

    List<EquipmentDTO> getAllEquipments();

    List<ModeTransportDTO> getAllModeTransport();

    List<SkillsDTO> getAllSkills();

    List<TaskDTO> getAllTasks();

    MasterFileDTO getMasterFile();

    List<EquipmentDTO> searchEquipments(String searchTerm);

    List<ModeTransportDTO> searchModeTransport(String searchTerm);

    List<SkillsDTO> searchSkills(String searchTerm);

    List<TaskDTO> searchTasks(String searchTerm);
}
