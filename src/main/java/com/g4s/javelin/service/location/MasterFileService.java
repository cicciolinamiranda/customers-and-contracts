package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterFileDTO;
import com.g4s.javelin.dto.core.masterfile.MethodOfRecordingDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.ProofOfDutyDTO;
import com.g4s.javelin.dto.core.masterfile.SkillsDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;

public interface MasterFileService {

    List<EquipmentDTO> getAllEquipments();

    List<ModeTransportDTO> getAllModeTransport();

    List<SkillsDTO> getAllSkills();

    List<TaskDTO> getAllTasks();

    List<ProofOfDutyDTO> getAllProofOfDuty();

    List<MethodOfRecordingDTO> getAllMethodOfRecording();

    MasterFileDTO getMasterFile();

    List<EquipmentDTO> searchEquipments(String searchTerm);

    List<ModeTransportDTO> searchModeTransport(String searchTerm);

    List<SkillsDTO> searchSkills(String searchTerm);

    List<TaskDTO> searchTasks(String searchTerm);

    List<ProofOfDutyDTO> searchProofOfDuty(String searchTerm);

    List<MethodOfRecordingDTO> searchMethodOfRecording(String searchTerm);

}
