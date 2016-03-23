package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.model.location.ModeTransportModel;
import com.g4s.javelin.data.model.location.SkillsModel;
import com.g4s.javelin.data.model.location.TaskModel;
import com.g4s.javelin.data.repository.location.EquipmentRepository;
import com.g4s.javelin.data.repository.location.ModeTransportRepository;
import com.g4s.javelin.data.repository.location.SkillsRepository;
import com.g4s.javelin.data.repository.location.TaskRepository;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

@Service
public class MasterFileServiceImpl implements MasterFileService {

    private ModelMapper modelMapper;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ModeTransportRepository modeRepository;
    @Autowired
    private SkillsRepository skillsRepository;
    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<EquipmentDTO> getAllEquipments() {
        Iterable<EquipmentModel> results = equipmentRepository.findAll();
        List<EquipmentDTO> response = Lists.newArrayList();

        for(EquipmentModel equipment: results) {
            response.add(modelMapper.map(equipment, EquipmentDTO.class));
        }

        return response;
    }

    @Override
    public List<ModeTransportDTO> getAllModeTransport() {
        Iterable<ModeTransportModel> results = modeRepository.findAll();
        List<ModeTransportDTO> response = Lists.newArrayList();

        for(ModeTransportModel modeTransport: results) {
            response.add(modelMapper.map(modeTransport, ModeTransportDTO.class));
        }

        return response;
    }

    @Override
    public List<SkillsDTO> getAllSkills() {
        Iterable<SkillsModel> results = skillsRepository.findAll();
        List<SkillsDTO> response = Lists.newArrayList();

        for(SkillsModel skills: results) {
            response.add(modelMapper.map(skills, SkillsDTO.class));
        }

        return response;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        Iterable<TaskModel> results = taskRepository.findAll();
        List<TaskDTO> response = Lists.newArrayList();

        for(TaskModel task: results) {
            response.add(modelMapper.map(task, TaskDTO.class));
        }

        return response;
    }

}
