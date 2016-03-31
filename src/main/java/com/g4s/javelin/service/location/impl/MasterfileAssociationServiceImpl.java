package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.location.CustomerLocationEquipmentModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.repository.location.CustomerLocationEquipmentRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.location.EquipmentRepository;
import com.g4s.javelin.data.repository.location.ModeTransportRepository;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.service.location.MasterfileAssociationService;
import com.google.common.collect.Lists;

public class MasterfileAssociationServiceImpl implements MasterfileAssociationService {

    @Autowired
    @Lazy
    private EquipmentRepository equipmentRepository;

    @Autowired
    @Lazy
    private ModeTransportRepository transportRepository;

    @Autowired
    @Lazy
    private CustomerLocationRepository locationRepository;

    @Autowired
    @Lazy
    private CustomerLocationEquipmentRepository locationEquipmentRepository;

    public void saveLocationEquipment(final Long customerLocationId, final List<EquipmentDTO> equipments) {
        List<CustomerLocationEquipmentModel> list = Lists.newArrayList();
        EquipmentModel model;
        CustomerLocationEquipmentModel locEquipmentModel;
        CustomerLocationModel locationModel = locationRepository.findOne(customerLocationId);
        for (EquipmentDTO dto : equipments) {
            model = equipmentRepository.findOne(dto.getId());
            locEquipmentModel = new CustomerLocationEquipmentModel();
            locEquipmentModel.setBilled(dto.isBilled());
            locEquipmentModel.setCostType(dto.getCostType());
            locEquipmentModel.setCustomerLocation(locationModel);
            locEquipmentModel.setEquipment(model);
            list.add(locEquipmentModel);
        }
        locationEquipmentRepository.save(list);
    }

    public List<EquipmentDTO> getLocationEquipments(final Long customerLocationId) {
        List<CustomerLocationEquipmentModel> results = locationEquipmentRepository.findByCustomerLocationId(customerLocationId);
        List<EquipmentDTO> list = Lists.newArrayList();
        EquipmentDTO equipment;
        if (!CollectionUtils.isEmpty(results)) {
            for (CustomerLocationEquipmentModel loc : results) {
                equipment = new EquipmentDTO();
                equipment.setId(loc.getEquipment().getId());
                equipment.setCostType(loc.getCostType());
                equipment.setEquipmentName(loc.getEquipment().getEquipmentName());
                equipment.setBilled(loc.isBilled());
                list.add(equipment);
            }
        }
        return list;
    }
}
