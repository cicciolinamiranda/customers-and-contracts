package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.data.model.location.CustomerLocationEquipmentModel;
import com.g4s.javelin.data.model.location.CustomerLocationModeOfTransportModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.masterfile.EquipmentModel;
import com.g4s.javelin.data.model.masterfile.ModeTransportModel;
import com.g4s.javelin.data.repository.location.CustomerLocationEquipmentRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationModeOfTransportRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.masterfile.EquipmentRepository;
import com.g4s.javelin.data.repository.masterfile.ModeTransportRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
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

    @Autowired
    @Lazy
    private CustomerLocationModeOfTransportRepository locationTransportRepository;

    @Override
    public void saveLocationEquipment(final Long customerLocationId, final List<EquipmentDTO> equipments) {
        if (!CollectionUtils.isEmpty(equipments)) {
            List<CustomerLocationEquipmentModel> list = Lists.newArrayList();
            EquipmentModel model;
            CustomerLocationEquipmentModel locEquipmentModel;
            CustomerLocationModel locationModel = locationRepository.findOne(customerLocationId);
            for (EquipmentDTO dto : equipments) {
                if (dto.isDeleted()) {
                    locationEquipmentRepository.delete(dto.getAssociationId());
                } else {
                    model = equipmentRepository.findOne(dto.getId());
                    locEquipmentModel = new CustomerLocationEquipmentModel();
                    locEquipmentModel.setId(dto.getAssociationId());
                    locEquipmentModel.setBilled(dto.isBilled());
                    locEquipmentModel.setCostType(dto.getCostType());
                    locEquipmentModel.setCustomerLocation(locationModel);
                    locEquipmentModel.setEquipment(model);
                    list.add(locEquipmentModel);
                }
            }
            if (list.size() > 0) {
                locationEquipmentRepository.save(list);
            }
        }
    }

    @Override
    public void saveLocationModeOfTransport(final Long customerLocationId, final List<ModeTransportDTO> transports) {
        if (!CollectionUtils.isEmpty(transports)) {
            List<CustomerLocationModeOfTransportModel> list = Lists.newArrayList();
            ModeTransportModel model;
            CustomerLocationModeOfTransportModel locTransportModel;
            CustomerLocationModel locationModel = locationRepository.findOne(customerLocationId);
            for (ModeTransportDTO dto : transports) {
                if (dto.isDeleted()) {
                    locationTransportRepository.delete(dto.getAssociationId());
                } else {
                    model = transportRepository.findOne(dto.getId());
                    locTransportModel = new CustomerLocationModeOfTransportModel();
                    locTransportModel.setId(dto.getAssociationId());
                    locTransportModel.setBilled(dto.isBilled());
                    locTransportModel.setCostType(dto.getCostType());
                    locTransportModel.setCustomerLocation(locationModel);
                    locTransportModel.setModeTransport(model);
                    list.add(locTransportModel);
                }
            }
            if (list.size() > 0) {
                locationTransportRepository.save(list);
            }
        }

    }

    public List<ModeTransportDTO> getLocationModeOfTransport(final Long customerLocationId) {
        List<ModeTransportDTO> list = Lists.newArrayList();
        ModeTransportDTO modeTransport;
        List<CustomerLocationModeOfTransportModel> transports = locationTransportRepository.findByCustomerLocationId(customerLocationId);
        if (!CollectionUtils.isEmpty(transports)) {
            for (CustomerLocationModeOfTransportModel loc : transports) {
                modeTransport = new ModeTransportDTO();
                modeTransport.setAssociationId(loc.getId());
                modeTransport.setId(loc.getModeTransport().getId());
                modeTransport.setCostType(loc.getCostType());
                modeTransport.setName(loc.getModeTransport().getName());
                modeTransport.setBilled(loc.isBilled());
                list.add(modeTransport);
            }
        }
        return list;
    }

    public List<EquipmentDTO> getLocationEquipments(final Long customerLocationId) {
        List<EquipmentDTO> list = Lists.newArrayList();
        EquipmentDTO equipment;
        List<CustomerLocationEquipmentModel> equipments = locationEquipmentRepository.findByCustomerLocationId(customerLocationId);
        if (!CollectionUtils.isEmpty(equipments)) {
            for (CustomerLocationEquipmentModel loc : equipments) {
                equipment = new EquipmentDTO();
                equipment.setAssociationId(loc.getId());
                equipment.setId(loc.getEquipment().getId());
                equipment.setCostType(loc.getCostType());
                equipment.setName(loc.getEquipment().getName());
                equipment.setBilled(loc.isBilled());
                list.add(equipment);
            }
        }
        return list;
    }


}
