package com.g4s.javelin.service.location.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.HibernateException;
import org.joda.time.format.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.g4s.javelin.constants.ExceptionMessageConstants;
import com.g4s.javelin.constants.ServiceConstants;
import com.g4s.javelin.data.model.location.AddressModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.SiteLocationModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.masterfile.TaskModel;
import com.g4s.javelin.data.model.mock.IncidentLogMockModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.workorder.WorkOrderRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.location.SiteLocationDTO;
import com.g4s.javelin.dto.core.location.WorkOrderDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.dto.mock.IncidentLogMockDTO;
import com.g4s.javelin.enums.StatusEnum;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.location.LocationMasterfileAssociationService;
import com.g4s.javelin.service.location.SiteLocationService;
import com.g4s.javelin.service.masterfile.MasterfileService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.api.client.util.Sets;

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
    private MasterfileService masterFileService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.SITE_LOCATION_SERVICE)
    private SiteLocationService siteLocationService;

    @Autowired
    @Lazy
    @Qualifier(ServiceConstants.LOCATION_MASTERFILE_ASSOC_SERVICE)
    private LocationMasterfileAssociationService masterfileAssociationService;

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
                .findByWorkOrdersIdAndStatusNot(workOrderId, StatusEnum.ARCHIVE);
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

    @Transactional(rollbackFor = {CustomerLocationException.class})
    @Override
    public CustomerLocationDTO saveCustomerLocationDetails(final CustomerLocationDTO customerLocation) throws CustomerLocationException {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        CustomerLocationModel model;
        Set<WorkOrderModel> workOrders = Sets.newHashSet();
        model = modelMapper.map(customerLocation,
                    CustomerLocationModel.class);
        if (customerLocation.getId() != null) {
            model.setId(customerLocation.getId());
        }
        WorkOrderModel workOrder = workOrderRepository.findOne(customerLocation.getWorkOrderId());
        workOrders.add(workOrder);

        model.setStartDate(dtf.parseDateTime(customerLocation.getStartDateStr()));
        if (customerLocation.getEndDateStr() != null) {
            model.setEndDate(dtf.parseDateTime(customerLocation.getEndDateStr()));
        }
        if (customerLocation.getLocationSurverDateStr() != null) {
            model.setLocationSurveyDate(dtf.parseDateTime(customerLocation.getLocationSurverDateStr()));
        }
        model.setWorkOrders(workOrders);
        model.setStatus(StatusEnum.findByCode(customerLocation.getStatusStr()));
        // setup equipments
        model.setSkills(transformSkillsToModel(customerLocation.getSkills()));
        model.setAddress(modelMapper.map(customerLocation.getAddress(),
                AddressModel.class));
        model.setIncidents(transformIncidentToModel(customerLocation.getIncidents()));
        model.setTasks(transformTasksToModel(customerLocation.getTasks()));
        try {
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
        } catch (HibernateException e) {
            throw new CustomerLocationException(e.getMessage());
        }
        return transformCustomerLocation(model);
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
        Set<WorkOrderModel> workOrders = customerLocation.getWorkOrders();
        workOrders.add(workOrder);
        customerLocation.setWorkOrders(workOrders);
        customerLocationRepository.save(customerLocation);
    }

    @Override
    public List<CustomerLocationDTO> searchAllCustomerLocations(final String searchTerm) {
        Long id = null;
        if (NumberUtils.isDigits(searchTerm)) {
            id = Long.valueOf(searchTerm);
        }
        String likeSearchTerm = "%" + searchTerm + "%";
        List<CustomerLocationModel> results = customerLocationRepository
                .findByStatusNotAndIdOrCustomerCustomerNameLikeOrAddressAddressLike(StatusEnum.ARCHIVE, id, likeSearchTerm, likeSearchTerm);
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
        dto.setEquipments(masterfileAssociationService.getLocationEquipments(model.getId()));
        dto.setStatusStr(model.getStatus().getCode());
        dto.setModeOfTransports(masterfileAssociationService.getLocationModeOfTransport(model.getId()));
        dto.setSkills(transformSkills(model.getSkills()));
        dto.setTasks(transformTasks(model.getTasks()));
        dto.setBarredEmployees(getBarredEmployeeDetails(model.getId()));
        dto.setSiteLocations(transformSiteLocation(model.getSiteLocations()));
        dto.setIncidents(transformIncident(model.getIncidents()));
        dto.setWorkOrderId(transformWorkOrder(model.getWorkOrders()).get(0).getId());
        return dto;
    }

    private List<MasterfileDTO> transformSkills(final Set<MasterfileModel> skills) {
        List<MasterfileDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(skills)) {
            for (MasterfileModel skill : skills) {
                list.add(modelMapper.map(skill, MasterfileDTO.class));
            }
        }
        return list;
    }

    private Set<MasterfileModel> transformSkillsToModel(final List<MasterfileDTO> skills) {
        Set<MasterfileModel> list = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(skills)) {
            for (MasterfileDTO skill : skills) {
                list.add(modelMapper.map(skill, MasterfileModel.class));
            }
        }
        return list;
    }

    private List<IncidentLogMockDTO> transformIncident(final Set<IncidentLogMockModel> incidents) {
        List<IncidentLogMockDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(incidents)) {
            for (IncidentLogMockModel skill : incidents) {
                list.add(modelMapper.map(skill, IncidentLogMockDTO.class));
            }
        }
        return list;
    }

    private List<WorkOrderDTO> transformWorkOrder(final Set<WorkOrderModel> workOrders) {
        List<WorkOrderDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(workOrders)) {
            for (WorkOrderModel skill : workOrders) {
                list.add(modelMapper.map(skill, WorkOrderDTO.class));
            }
        }
        return list;
    }

    private Set<IncidentLogMockModel> transformIncidentToModel(final List<IncidentLogMockDTO> incidents) {
        Set<IncidentLogMockModel> list = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(incidents)) {
            for (IncidentLogMockDTO skill : incidents) {
                list.add(modelMapper.map(skill, IncidentLogMockModel.class));
            }
        }
        return list;
    }

    private List<TaskDTO> transformTasks(final Set<TaskModel> tasks) {
        List<TaskDTO> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(tasks)) {
            for (TaskModel task : tasks) {
                list.add(modelMapper.map(task, TaskDTO.class));
            }
        }
        return list;
    }

    private Set<TaskModel> transformTasksToModel(final List<TaskDTO> tasks) {
        Set<TaskModel> list = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(tasks)) {
            for (TaskDTO task : tasks) {
                list.add(modelMapper.map(task, TaskModel.class));
            }
        }
        return list;
    }

    private List<SiteLocationDTO> transformSiteLocation(final Set<SiteLocationModel> siteLocations) {
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
}
