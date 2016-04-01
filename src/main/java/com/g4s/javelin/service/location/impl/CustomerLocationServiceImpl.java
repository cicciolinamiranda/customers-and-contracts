package com.g4s.javelin.service.location.impl;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.constants.ExceptionMessageConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.data.model.location.AddressModel;
import com.g4s.javelin.data.model.location.CustomerLocationEquipmentModel;
import com.g4s.javelin.data.model.location.CustomerLocationModeOfTransportModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.SiteLocationModel;
import com.g4s.javelin.data.model.location.SkillsModel;
import com.g4s.javelin.data.model.location.TaskModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.workorder.WorkOrderRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CreateCustomerLocationDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SiteLocationDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.enums.StatusEnum;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.location.MasterFileService;
import com.g4s.javelin.service.location.MasterfileAssociationService;
import com.g4s.javelin.service.location.SiteLocationService;
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

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.MASTER_FILE_SERVICE)
    private MasterFileService masterFileService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.SITE_LOCATION_SERVICE)
    private SiteLocationService siteLocationService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.MASTERFILE_ASSOC_SERVICE)
    private MasterfileAssociationService masterfileAssociationService;

    private ModelMapper modelMapper;

    public CustomerLocationServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public CustomerLocationDTO getCustomerLocationDetails(
            final Long customerLocationId) {
        CustomerLocationModel result = customerLocationRepository
                .findOne(customerLocationId);
        return transformCustomerLocation(result);
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationDetailsList(
            final Long workOrderId) {
        List<CustomerLocationModel> results = customerLocationRepository
                .findByWorkOrdersId(workOrderId);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationByAddress(final String address) {
        List<CustomerLocationModel> results = customerLocationRepository.
                findByAddressAddressContainingIgnoreCase(address);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public List<CustomerLocationDTO> getCustomerLocationByCustomerName(
            final String customerName) {
        List<CustomerLocationModel> results = customerLocationRepository.
                findByCustomerCustomerNameContainingIgnoreCase(customerName);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public void saveCustomerLocationDetails(final CustomerLocationDTO customerLocation) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        CustomerLocationModel model;
        List<WorkOrderModel> workOrders = Lists.newArrayList();
        model = modelMapper.map(customerLocation,
                    CustomerLocationModel.class);
        if (customerLocation.getId() != null) {
            model.setId(customerLocation.getId());
        }
        WorkOrderModel workOrder = workOrderRepository.findOne(customerLocation.getWorkOrderId());
        workOrders.add(workOrder);
        //setup createdDate
        if (model.getCreatedDate() == null) {
            model.setCreatedDate(DateTime.now());
        }
        model.setStartDate(dtf.parseDateTime(customerLocation.getStartDateStr()));
        if (customerLocation.getEndDateStr() != null) {
            model.setEndDate(dtf.parseDateTime(customerLocation.getEndDateStr()));
        }
        model.setWorkOrders(workOrders);
        model.setStatus(StatusEnum.findByCode(customerLocation.getStatusStr()));
        // setup equipments
        model.setSkills(transformSkillsToModel(customerLocation.getSkills()));
        model.setAddress(modelMapper.map(customerLocation.getAddress(),
                AddressModel.class));
        model.setTasks(transformTasksToModel(customerLocation.getTasks()));
        model = customerLocationRepository.save(model);
        // Save barred employess
        barredEmployeeService.saveBarredEmployees(
                customerLocation.getBarredEmployees(), model.getId());
        // Save Site Location Details
        siteLocationService.saveSiteLocation(model.getId(), customerLocation.getSiteLocations());
        // Save Location Equipments
        masterfileAssociationService.saveLocationEquipment(model.getId(), customerLocation.getEquipments());
        // Save Location Mode of Transport
        masterfileAssociationService.saveLocationModeOfTransport(model.getId(), customerLocation.getModeOfTransports());

    }

    @Override
    public void addExistingCustomerLocationToAWorkOrder(
            final Long customerLocationId, final Long workOrderId)
            throws CustomerLocationException {
        WorkOrderModel workOrder = workOrderRepository.findOne(workOrderId);

        if (workOrder == null) {
            throw new CustomerLocationException(ExceptionMessageConstants.NULL_WORKORDER_FOUND);
        }

        CustomerLocationModel customerLocation = customerLocationRepository.findOne(customerLocationId);
        if (customerLocation == null) {
            throw new CustomerLocationException(ExceptionMessageConstants.NULL_EXISTING_CUSTLOC_FOUND);
        }
        List<WorkOrderModel> workOrders = customerLocation.getWorkOrders();
        workOrders.add(workOrder);
        customerLocation.setWorkOrders(workOrders);
        customerLocationRepository.save(customerLocation);
    }

    @Override
    public CreateCustomerLocationDTO createCustomerLocation() {
        CustomerLocationModel result = customerLocationRepository.save(new CustomerLocationModel());
        CreateCustomerLocationDTO dto = new CreateCustomerLocationDTO();
        dto.setCustomerLocationId(result.getId());
        dto.setMasterfile(masterFileService.getMasterFile());
        return dto;
    }

    @Override
    public List<CustomerLocationDTO> searchAllCustomerLocations(final String searchTerm) {
        Long id = null;
        if (NumberUtils.isDigits(searchTerm)) {
            id = Long.valueOf(searchTerm);
        }
        String likeSearchTerm = "%" + searchTerm + "%";
        List<CustomerLocationModel> results = customerLocationRepository
                .findByIdOrCustomerCustomerNameLikeOrAddressAddressLike(id, likeSearchTerm, likeSearchTerm);
        List<CustomerLocationDTO> list = Lists.newArrayList();
        for (CustomerLocationModel result : results) {
            list.add(transformCustomerLocation(result));
        }
        return list;
    }

    @Override
    public void updateCustomerLocationStatus(final Long id, final String status) {
        StatusEnum statusEnum = StatusEnum.findByCode(status);
        customerLocationRepository.updateStatus(id, statusEnum);
    }

    private CustomerLocationDTO transformCustomerLocation(
            final CustomerLocationModel model) {
        CustomerLocationDTO dto = new CustomerLocationDTO();
        dto = modelMapper.map(model, CustomerLocationDTO.class);
        dto.setEquipments(getLocationEquipments(model.getLocationEquipments()));
        dto.setModeOfTransports(getLocationModeOfTransport(model.getLocationTransports()));
        dto.setSkills(transformSkills(model.getSkills()));
        dto.setTasks(transformTasks(model.getTasks()));
        dto.setBarredEmployees(getBarredEmployeeDetails(model.getId()));
        dto.setSiteLocations(transformSiteLocation(model.getSiteLocations()));
        return dto;
    }

    private List<SkillsDTO> transformSkills(final List<SkillsModel> skills) {
        List<SkillsDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(skills)) {
            for (SkillsModel skill : skills) {
                list.add(modelMapper.map(skill, SkillsDTO.class));
            }
        }
        return list;
    }

    private List<SkillsModel> transformSkillsToModel(final List<SkillsDTO> skills) {
        List<SkillsModel> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(skills)) {
            for (SkillsDTO skill : skills) {
                list.add(modelMapper.map(skill, SkillsModel.class));
            }
        }
        return list;
    }

    private List<TaskDTO> transformTasks(final List<TaskModel> tasks) {
        List<TaskDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(tasks)) {
            for (TaskModel task : tasks) {
                list.add(modelMapper.map(task, TaskDTO.class));
            }
        }
        return list;
    }

    private List<TaskModel> transformTasksToModel(final List<TaskDTO> tasks) {
        List<TaskModel> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(tasks)) {
            for (TaskDTO task : tasks) {
                list.add(modelMapper.map(task, TaskModel.class));
            }
        }
        return list;
    }

    private List<SiteLocationDTO> transformSiteLocation(final List<SiteLocationModel> siteLocations) {
        List<SiteLocationDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(siteLocations)) {
            for (SiteLocationModel slm : siteLocations) {
                list.add(modelMapper.map(slm, SiteLocationDTO.class));
            }
        }
        return list;
    }

    private List<BarredEmployeeDTO> getBarredEmployeeDetails(
            final Long customerLocationId) {
        return barredEmployeeService.getBarredEmployees(customerLocationId);
    }

    public List<ModeTransportDTO> getLocationModeOfTransport(final List<CustomerLocationModeOfTransportModel> transports) {
        List<ModeTransportDTO> list = Lists.newArrayList();
        ModeTransportDTO modeTransport;
        if (!CollectionUtils.isEmpty(transports)) {
            for (CustomerLocationModeOfTransportModel loc : transports) {
                modeTransport = new ModeTransportDTO();
                modeTransport.setId(loc.getModeTransport().getId());
                modeTransport.setCostType(loc.getCostType());
                modeTransport.setTransportName(loc.getModeTransport().getTransportName());
                modeTransport.setBilled(loc.isBilled());
                list.add(modeTransport);
            }
        }
        return list;
    }

    public List<EquipmentDTO> getLocationEquipments(final List<CustomerLocationEquipmentModel> equipments) {
        List<EquipmentDTO> list = Lists.newArrayList();
        EquipmentDTO equipment;
        if (!CollectionUtils.isEmpty(equipments)) {
            for (CustomerLocationEquipmentModel loc : equipments) {
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
