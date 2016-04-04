package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.model.location.MethodOfRecordingModel;
import com.g4s.javelin.data.model.location.ModeTransportModel;
import com.g4s.javelin.data.model.location.ProofOfDutyModel;
import com.g4s.javelin.data.model.location.SkillsModel;
import com.g4s.javelin.data.model.location.TaskModel;
import com.g4s.javelin.data.repository.location.EquipmentRepository;
import com.g4s.javelin.data.repository.location.MethodOfRecordingRepository;
import com.g4s.javelin.data.repository.location.ModeTransportRepository;
import com.g4s.javelin.data.repository.location.ProofOfDutyRepository;
import com.g4s.javelin.data.repository.location.SkillsRepository;
import com.g4s.javelin.data.repository.location.TaskRepository;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.MasterFileDTO;
import com.g4s.javelin.dto.core.location.MethodOfRecordingDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.ProofOfDutyDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.service.location.MasterFileService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class MasterFileServiceImpl implements MasterFileService {

    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private EquipmentRepository equipmentRepository;

    @Autowired
    @Lazy
    private ModeTransportRepository modeRepository;

    @Autowired
    @Lazy
    private SkillsRepository skillsRepository;

    @Autowired
    @Lazy
    private TaskRepository taskRepository;

    @Autowired
    @Lazy
    private ProofOfDutyRepository proofOfDutyRepository;

    @Autowired
    @Lazy
    private MethodOfRecordingRepository methodOfRecordingRepository;

    public MasterFileServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        Iterable<EquipmentModel> results = equipmentRepository.findAll();
        List<EquipmentDTO> response = Lists.newArrayList();

        for (EquipmentModel equipment : results) {
            response.add(modelMapper.map(equipment, EquipmentDTO.class));
        }

        return response;
    }

    @Override
    public List<ModeTransportDTO> getAllModeTransport() {
        Iterable<ModeTransportModel> results = modeRepository.findAll();
        List<ModeTransportDTO> response = Lists.newArrayList();

        for (ModeTransportModel modeTransport : results) {
            response.add(modelMapper.map(modeTransport, ModeTransportDTO.class));
        }

        return response;
    }

    @Override
    public List<SkillsDTO> getAllSkills() {
        Iterable<SkillsModel> results = skillsRepository.findAll();
        List<SkillsDTO> response = Lists.newArrayList();

        for (SkillsModel skills : results) {
            response.add(modelMapper.map(skills, SkillsDTO.class));
        }

        return response;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        Iterable<TaskModel> results = taskRepository.findAll();
        List<TaskDTO> response = Lists.newArrayList();

        for (TaskModel task : results) {
            response.add(modelMapper.map(task, TaskDTO.class));
        }

        return response;
    }
    @Override
    public List<ProofOfDutyDTO> getAllProofOfDuty() {
        Iterable<ProofOfDutyModel> results = proofOfDutyRepository.findAll();
        List<ProofOfDutyDTO> response = Lists.newArrayList();

        for (ProofOfDutyModel pod : results) {
            response.add(modelMapper.map(pod, ProofOfDutyDTO.class));
        }
        return response;
    }

    @Override
    public List<MethodOfRecordingDTO> getAllMethodOfRecording() {
        Iterable<MethodOfRecordingModel> results = methodOfRecordingRepository.findAll();
        List<MethodOfRecordingDTO> response = Lists.newArrayList();

        for (MethodOfRecordingModel mor : results) {
            response.add(modelMapper.map(mor, MethodOfRecordingDTO.class));
        }
        return response;
    }

    @Override
    public MasterFileDTO getMasterFile() {
        MasterFileDTO dto = new MasterFileDTO();
        dto.setEquipments(getAllEquipments());
        dto.setModeTransport(getAllModeTransport());
        dto.setSkills(getAllSkills());
        dto.setTasks(getAllTasks());
        return dto;
    }

    @Override
    public List<EquipmentDTO> searchEquipments(final String searchTerm) {
        List<EquipmentModel> results = equipmentRepository.findByEquipmentNameContainingIgnoreCase(searchTerm);
        List<EquipmentDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (EquipmentModel equipment : results) {
                response.add(modelMapper.map(equipment, EquipmentDTO.class));
            }
        }
        return response;
    }

    @Override
    public List<ModeTransportDTO> searchModeTransport(final String searchTerm) {
        List<ModeTransportModel> results = modeRepository.findByTransportNameContainingIgnoreCase(searchTerm);
        List<ModeTransportDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (ModeTransportModel modeTransport : results) {
                response.add(modelMapper.map(modeTransport, ModeTransportDTO.class));
            }
        }
        return response;
    }

    @Override
    public List<SkillsDTO> searchSkills(final String searchTerm) {
        List<SkillsModel> results = skillsRepository.findBySkillNameContainingIgnoreCase(searchTerm);
        List<SkillsDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (SkillsModel skills : results) {
                response.add(modelMapper.map(skills, SkillsDTO.class));
            }
        }

        return response;

    }

    @Override
    public List<TaskDTO> searchTasks(final String searchTerm) {
        List<TaskModel> results = taskRepository.findByTaskNameContainingIgnoreCase(searchTerm);
        List<TaskDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (TaskModel task : results) {
                response.add(modelMapper.map(task, TaskDTO.class));
            }
        }
        return response;
    }
    @Override
    public List<ProofOfDutyDTO> searchProofOfDuty(final String searchTerm) {
        List<ProofOfDutyModel> results = proofOfDutyRepository.findByNameContainingIgnoreCase(searchTerm);
        List<ProofOfDutyDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (ProofOfDutyModel pod : results) {
                response.add(modelMapper.map(pod, ProofOfDutyDTO.class));
            }
        }
        return response;
    }

    @Override
    public List<MethodOfRecordingDTO> searchMethodOfRecording(final String searchTerm) {
        List<MethodOfRecordingModel> results = methodOfRecordingRepository.findByNameContainingIgnoreCase(searchTerm);
        List<MethodOfRecordingDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (MethodOfRecordingModel mor : results) {
                response.add(modelMapper.map(mor, MethodOfRecordingDTO.class));
            }
        }
        return response;
    }


}
