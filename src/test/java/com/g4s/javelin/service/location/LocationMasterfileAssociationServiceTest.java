package com.g4s.javelin.service.location;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.location.CustomerLocationEquipmentModel;
import com.g4s.javelin.data.model.location.CustomerLocationModeOfTransportModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.repository.location.CustomerLocationEquipmentRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationModeOfTransportRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.masterfile.MasterfileRepository;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.enums.CostTypeEnum;
import com.g4s.javelin.service.location.impl.LocationMasterfileAssociationServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

@RunWith(MockitoJUnitRunner.class)
public class LocationMasterfileAssociationServiceTest {

    @Mock
    private MasterfileRepository masterfileRepository;

    @Mock
    private CustomerLocationRepository locationRepository;

    @Mock
    private CustomerLocationEquipmentRepository locationEquipmentRepository;

    @Mock
    private CustomerLocationModeOfTransportRepository locationTransportRepository;

    @InjectMocks
    private LocationMasterfileAssociationService service = new LocationMasterfileAssociationServiceImpl();
    
    private List<EquipmentDTO> equipments = Lists.newArrayList();
    private List<ModeTransportDTO> transports = Lists.newArrayList();
    private List<CustomerLocationEquipmentModel> models = Lists.newArrayList();
    private List<CustomerLocationModeOfTransportModel> transportModel = Lists.newArrayList();
    private CustomerLocationModel location = new CustomerLocationModel();
	MasterfileModel masterfile = new MasterfileModel();

    @Before
    public void init() {
    	setUpEquipments();
    	setUpCustomerLocationEquipment();
    	setUpCustomerLocation();
    	setUpCustomerLocationTransport();
    	setUpTransports();
    }

    private void setUpEquipments() {
        
    	EquipmentDTO equipment = new EquipmentDTO();
    	equipment.setBilled(true);
    	equipment.setCostType(CostTypeEnum.ONEOFFCOST.getCode());
    	equipment.setId(1L);
    	equipment.setName("Gun");
    	equipment.setAssociationId(1L);
    	equipments.add(equipment);
    }

    private void setUpCustomerLocationEquipment() {
    	CustomerLocationEquipmentModel model = new CustomerLocationEquipmentModel();
    	model.setId(1L);
    	masterfile.setId(1L);
    	masterfile.setName("Gun");
    	model.setEquipment(masterfile);
    	model.setCostType(CostTypeEnum.ONEOFFCOST.getCode());
    	model.setBilled(true);
    	models.add(model);
    }

    private void setUpTransports() {
        
    	ModeTransportDTO transport = new ModeTransportDTO();
    	transport.setBilled(true);
    	transport.setCostType(CostTypeEnum.ONEOFFCOST.getCode());
    	transport.setId(1L);
    	transport.setName("Gun");
    	transport.setAssociationId(1L);
    	transports.add(transport);
    }

    private void setUpCustomerLocationTransport() {
    	CustomerLocationModeOfTransportModel model = new CustomerLocationModeOfTransportModel();
    	model.setId(1L);
    	masterfile.setId(1L);
    	masterfile.setName("Gun");
    	model.setModeTransport(masterfile);
    	model.setCostType(CostTypeEnum.ONEOFFCOST.getCode());
    	model.setBilled(true);
    	transportModel.add(model);
    }

    private void setUpCustomerLocation() {
    	location.setId(1L);
    }
    @Test
    public void testSaveLocationEquipment() {
    	EquipmentDTO equipment = new EquipmentDTO();
    	equipment.setDeleted(true);
    	equipments.add(equipment);
    	when(locationRepository.findOne(Mockito.anyLong())).thenReturn(location);
    	when(masterfileRepository.findOne(Mockito.anyLong())).thenReturn(masterfile);
    	when(locationEquipmentRepository.save(models)).thenReturn(models);
    	service.saveLocationEquipment(1L, equipments);
    	verify(masterfileRepository, times(1)).findOne(Mockito.anyLong());    
    	verify(locationEquipmentRepository, times(1)).delete(Mockito.anyLong());
    }

    @Test
    public void testSaveLocationTransport() {
    	ModeTransportDTO transport = new ModeTransportDTO();
    	transport.setDeleted(true);
    	transports.add(transport);
    	when(locationRepository.findOne(Mockito.anyLong())).thenReturn(location);
    	when(masterfileRepository.findOne(Mockito.anyLong())).thenReturn(masterfile);
    	when(locationTransportRepository.save(transportModel)).thenReturn(transportModel);
    	service.saveLocationModeOfTransport(1L, transports);
    	verify(masterfileRepository, times(1)).findOne(Mockito.anyLong());    
    	verify(locationTransportRepository, times(1)).delete(Mockito.anyLong());
    }

    @Test
    public void testGetAllCustomerLocationEquipment() {
    	when(locationEquipmentRepository.findByCustomerLocationId(Mockito.anyLong())).thenReturn(models);
    	List<EquipmentDTO> response = service.getLocationEquipments(1L);
    	Assert.assertEquals(1, response.size());
    }

    @Test
    public void testGetAllTransport() {
    	when(locationTransportRepository.findByCustomerLocationId(Mockito.anyLong())).thenReturn(transportModel);
    	List<ModeTransportDTO> response = service.getLocationModeOfTransport(1L);
    	Assert.assertEquals(1, response.size());
    }
}

