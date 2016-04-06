package com.g4s.javelin.service.masterfile.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.masterfile.TaskModel;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.data.repository.masterfile.TaskRepository;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.MasterfileTypeEnum;
import com.g4s.javelin.service.masterfile.MasterfileService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class MasterfileServiceImpl implements MasterfileService {

    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private TaskRepository taskRepository;

    @Autowired
    @Lazy
    private MasterfileRepository masterfileRepository;

    public MasterfileServiceImpl() {
        modelMapper = new ModelMapper();
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
    public List<TaskDTO> searchTasks(final String searchTerm) {
        Iterable<TaskModel> results = taskRepository.findByNameContainingIgnoreCase(searchTerm);
        List<TaskDTO> response = Lists.newArrayList();

        for (TaskModel task : results) {
            response.add(modelMapper.map(task, TaskDTO.class));
        }

        return response;
    }

    @Override
    public List<MasterfileDTO> searchMasterfileByTypeAndName(final MasterfileTypeEnum type, final String searchTerm) {
        List<MasterfileModel> results = masterfileRepository
                .findByTypeAndNameContainingIgnoreCase(type, searchTerm);
        List<MasterfileDTO> response = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(results)) {
            for (MasterfileModel masterfile : results) {
                response.add(modelMapper.map(masterfile, MasterfileDTO.class));
            }
        }
        return response;
    }

    public List<MasterfileDTO> getMasterfilesByType(final MasterfileTypeEnum type) {
        Iterable<MasterfileModel> results = masterfileRepository.findByType(type);
        List<MasterfileDTO> response = Lists.newArrayList();

        for (MasterfileModel masterfile : results) {
            response.add(modelMapper.map(masterfile, MasterfileDTO.class));
        }

        return response;
    }

}
