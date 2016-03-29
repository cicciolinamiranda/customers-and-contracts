package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ExceptionMessageConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.data.model.location.AddressModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.model.location.ModeTransportModel;
import com.g4s.javelin.data.model.location.SkillsModel;
import com.g4s.javelin.data.model.location.TaskModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.workorder.WorkOrderRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

public class CustomerLocationServiceImpl implements CustomerLocationService {

    @Autowired
    @Lazy
    private CustomerLocationRepository customerLocationRepository;

    @Autowired
    @Lazy
    private WorkOrderRepository workOrderRepository;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.BARRED_EMPLOYEE_SERVICE)
    private BarredEmployeeService barredEmployeeService;

    private ModelMapper modelMapper;

    public CustomerLocationServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public CustomerLocationDTO getCustomerLocationDetails(
            Long customerLocationId) {
        CustomerLocationModel result = customerLocationRepository
                .findOne(customerLocationId);
        return transformCustomerLocation(result);
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationDetailsList(
            Long workOrderId) {
        List<CustomerLocationModel> results = customerLocationRepository
                .findByWorkOrdersId(workOrderId);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationByAddress(String address) {
        List<CustomerLocationModel> results = customerLocationRepository.
                findByAddressAddressLike(address);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationByCustomerName(
            String customerName) {
        List<CustomerLocationModel> results = customerLocationRepository.
        		findByCustomerCustomerName(customerName);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public void saveCustomerLocationDetails(CustomerLocationDTO customerLocation) {
    	System.out.println(customerLocation.getCreatedDate());
        CustomerLocationModel model;
        List<WorkOrderModel> workOrders = Lists.newArrayList();
        model = modelMapper.map(customerLocation,
                    CustomerLocationModel.class);
        if (customerLocation.getId() != null) {
            model.setId(customerLocation.getId());
        }
        WorkOrderModel workOrder = workOrderRepository.findOne(customerLocation.getWorkOrderId());
        workOrders.add(workOrder);
        model.setWorkOrders(workOrders);

        // setup equipments
        model.setEquipments(transformEquipmentsToModel(customerLocation
                .getEquipments()));
        model.setSkills(transformSkillsToModel(customerLocation.getSkills()));
        model.setAddress(modelMapper.map(customerLocation.getAddress(),
                AddressModel.class));
        model.setTasks(transformTasksToModel(customerLocation.getTasks()));
        model.setModeTransports(transformModeTransportToModel(customerLocation
                .getModeOfTransports()));
        System.out.println(model.toString());
        model = customerLocationRepository.save(model);
        // Save barred employess
        barredEmployeeService.saveBarredEmployees(
                customerLocation.getBarredEmployees(), model.getId());
    }

    @Override
    public void addExistingCustomerLocationToAWorkOrder(
            Long customerLocationId, Long workOrderId)
            throws CustomerLocationException {
        WorkOrderModel workOrder = workOrderRepository.findOne(workOrderId);

        if(workOrder == null)
        {
            throw new CustomerLocationException(ExceptionMessageConstants.NULL_WORKORDER_FOUND);
        }

        CustomerLocationModel customerLocation = customerLocationRepository.findOne(customerLocationId);
        if(customerLocation == null)
        {
            throw new CustomerLocationException(ExceptionMessageConstants.NULL_EXISTING_CUSTLOC_FOUND);
        }
        List<WorkOrderModel> workOrders = customerLocation.getWorkOrders();
        workOrders.add(workOrder);
        customerLocation.setWorkOrders(workOrders);
        customerLocationRepository.save(customerLocation);
    }

    private CustomerLocationDTO transformCustomerLocation(
            CustomerLocationModel model) {
        CustomerLocationDTO dto = new CustomerLocationDTO();
        dto = modelMapper.map(model, CustomerLocationDTO.class);
        dto.setEquipments(transformEquipments(model.getEquipments()));
        dto.setModeOfTransports(transformModeTransport(model
                .getModeTransports()));
        dto.setSkills(transformSkills(model.getSkills()));
        dto.setTasks(transformTasks(model.getTasks()));
        dto.setBarredEmployees(getBarredEmployeeDetails(model.getId()));
        return dto;
    }

    private List<EquipmentDTO> transformEquipments(
            List<EquipmentModel> equipments) {
        List<EquipmentDTO> list = Lists.newArrayList();
        for (EquipmentModel equipment : equipments) {
            list.add(modelMapper.map(equipment, EquipmentDTO.class));
        }
        return list;
    }

    private List<EquipmentModel> transformEquipmentsToModel(
            List<EquipmentDTO> equipments) {
        List<EquipmentModel> list = Lists.newArrayList();
        for (EquipmentDTO equipment : equipments) {
            list.add(modelMapper.map(equipment, EquipmentModel.class));
        }
        return list;
    }

    private List<SkillsDTO> transformSkills(List<SkillsModel> skills) {
        List<SkillsDTO> list = Lists.newArrayList();
        for (SkillsModel skill : skills) {
            list.add(modelMapper.map(skill, SkillsDTO.class));
        }
        return list;
    }

    private List<SkillsModel> transformSkillsToModel(List<SkillsDTO> skills) {
        List<SkillsModel> list = Lists.newArrayList();
        for (SkillsDTO skill : skills) {
            list.add(modelMapper.map(skill, SkillsModel.class));
        }
        return list;
    }

    private List<ModeTransportDTO> transformModeTransport(
            List<ModeTransportModel> modeTransport) {
        List<ModeTransportDTO> list = Lists.newArrayList();
        for (ModeTransportModel model : modeTransport) {
            list.add(modelMapper.map(model, ModeTransportDTO.class));
        }
        return list;
    }

    private List<ModeTransportModel> transformModeTransportToModel(
            List<ModeTransportDTO> modeTransport) {
        List<ModeTransportModel> list = Lists.newArrayList();
        for (ModeTransportDTO model : modeTransport) {
            list.add(modelMapper.map(model, ModeTransportModel.class));
        }
        return list;
    }

    private List<TaskDTO> transformTasks(List<TaskModel> tasks) {
        List<TaskDTO> list = Lists.newArrayList();
        for (TaskModel task : tasks) {
            list.add(modelMapper.map(task, TaskDTO.class));
        }
        return list;
    }

    private List<TaskModel> transformTasksToModel(List<TaskDTO> tasks) {
        List<TaskModel> list = Lists.newArrayList();
        for (TaskDTO task : tasks) {
            list.add(modelMapper.map(task, TaskModel.class));
        }
        return list;
    }

    private List<BarredEmployeeDTO> getBarredEmployeeDetails(
            Long customerLocationId) {
        return barredEmployeeService.getBarredEmployees(customerLocationId);
    }
}
