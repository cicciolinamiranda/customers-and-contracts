package com.g4s.javelin.data.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.repository.location.EquipmentRepository;
import com.g4s.javelin.data.service.location.MasterFileService;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

@Service
public class MasterFileServiceImpl implements MasterFileService {

    private ModelMapper modelMapper;
    @Autowired
    private EquipmentRepository equipmentRepository;


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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<SkillsDTO> getAllSkills() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        // TODO Auto-generated method stub
        return null;
    }

}
