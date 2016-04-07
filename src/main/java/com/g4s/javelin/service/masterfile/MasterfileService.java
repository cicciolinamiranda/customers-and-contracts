package com.g4s.javelin.service.masterfile;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;

public interface MasterfileService {

    List<TaskDTO> getAllTasks();

    List<TaskDTO> searchTasks(String searchTerm);

    List<MasterfileDTO> searchMasterfileByTypeAndName(MasterfileTypeEnum type, String searchTerm);

    List<MasterfileDTO> getMasterfilesByType(MasterfileTypeEnum type);

}
