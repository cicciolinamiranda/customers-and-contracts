package com.g4s.javelin.api.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.location.CustomerLocationApi;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.dto.core.location.AddressDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

/**
 * @author Jordan Duabe
 * @since 03/29/2016
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerLocationApiTest {

    @Mock
    private CustomerLocationService customerLocationService;

    @Mock
    private CustomerLocationDTO customerLocationDTO;

    @Mock
    private List<CustomerLocationDTO> customerLocationList;

    @InjectMocks
    private CustomerLocationApi customerLocationApi = new CustomerLocationApi();

    @Test
    public void testGetCustomerLocationDetails() throws Exception {
        Mockito.when(customerLocationService.getCustomerLocationDetails(Mockito.anyLong()))
                .thenReturn(customerLocationDTO);
        assertNotNull(customerLocationApi.getCustomerLocationDetails(1L));
    }

    @Before
    public void initMocks() {
        setUpCustomerLocationDTO();
    }


    private void setUpCustomerLocationDTO() {
        customerLocationDTO = new CustomerLocationDTO();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddress("1234 Jupiter Street, Manila");
        addressDTO.setLatitude("0");
        addressDTO.setLongitude("0");
        addressDTO.setPostCode("1234");
        customerLocationDTO.setAddress(addressDTO);
        List<BarredEmployeeDTO> barredEmployees = Lists.newArrayList();
        BarredEmployeeDTO barredEmployeeDTO = new BarredEmployeeDTO();
        barredEmployeeDTO.setEmployeeId(1234l);
        barredEmployeeDTO.setFirstName("Juan");
        barredEmployeeDTO.setLastName("Dela Cruz");
        barredEmployees.add(barredEmployeeDTO);
        customerLocationDTO.setBarredEmployees(barredEmployees);
        customerLocationDTO.setCreatedDate(new DateTime());
        List<EquipmentDTO> equipments = Lists.newArrayList();
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setEquipmentName("Gun");
        equipments.add(equipmentDTO);
        customerLocationDTO.setEquipments(equipments);
        customerLocationDTO.setFloorPlan("Floor Plan");
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerName("Juan Dela Cruz");
        List<ModeTransportDTO> modeTransports = Lists.newArrayList();
        customerLocationDTO.setModeOfTransports(modeTransports);
        List<SkillsDTO> skills = Lists.newArrayList();
        customerLocationDTO.setSkills(skills);
        List<TaskDTO> tasks = Lists.newArrayList();
        customerLocationDTO.setTasks(tasks);
        customerLocationDTO.setCustomer(customer);
    }
    @Test
    public void testGetCustomerLocationDetailsList() throws Exception {
        Mockito.when(customerLocationService.getCustomerLocationDetailsList(Mockito.anyLong()))
                .thenReturn(customerLocationList);
        assertNotNull(customerLocationApi.getCustomerLocationDetailsList(1L));
    }

    @Test
    public void testSaveCustomerLocationDetails() throws Exception {
        customerLocationApi.saveCustomerLocationDetails(customerLocationDTO);
        Mockito.verify(customerLocationService, Mockito.times(1)).saveCustomerLocationDetails(
                Mockito.any(CustomerLocationDTO.class));
    }
    
    @Test
    public void testSearchLocationByAddress() {
    	List<CustomerLocationDTO> list = Lists.newArrayList();
    	list.add(customerLocationDTO);
    	when(customerLocationService.getCustomerLocationByAddress(Mockito.anyString())).thenReturn(list);
    	List<CustomerLocationDTO> result = customerLocationApi.searchCustomerLocation(1L, "ADDRESS", "value");
    	assertEquals(1, result.size());
    }
    
    @Test
    public void testSearchLocationByCustomer() {
    	List<CustomerLocationDTO> list = Lists.newArrayList();
    	list.add(customerLocationDTO);
    	when(customerLocationService.getCustomerLocationByCustomerName(Mockito.anyString())).thenReturn(list);
    	List<CustomerLocationDTO> result = customerLocationApi.searchCustomerLocation(1L, "CUSTOMER", "value");
    	assertEquals(1, result.size());
    }
    @Test
    public void testSearchLocationById() {
    	when(customerLocationService.getCustomerLocationDetails(Mockito.anyLong())).thenReturn(customerLocationDTO);
    	List<CustomerLocationDTO> result = customerLocationApi.searchCustomerLocation(1L, "ID", "1");
    	assertEquals(1, result.size());
    }
}