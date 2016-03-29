package com.g4s.javelin.data.service.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.location.AddressModel;
import com.g4s.javelin.data.model.location.BarredEmployeeModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.model.location.CustomerModel;
import com.g4s.javelin.data.model.location.EquipmentModel;
import com.g4s.javelin.data.model.location.ModeTransportModel;
import com.g4s.javelin.data.model.location.SkillsModel;
import com.g4s.javelin.data.model.location.TaskModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.workorder.WorkOrderRepository;
import com.g4s.javelin.dto.core.location.AddressDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.location.EquipmentDTO;
import com.g4s.javelin.dto.core.location.ModeTransportDTO;
import com.g4s.javelin.dto.core.location.SkillsDTO;
import com.g4s.javelin.dto.core.location.TaskDTO;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.CustomerLocationService;
import com.g4s.javelin.service.location.impl.CustomerLocationServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

@RunWith(MockitoJUnitRunner.class)
public class CustomerLocationServiceTest {

    @Mock
    private CustomerLocationRepository customerLocationRepositoryMock;

    @Mock
    private WorkOrderRepository workOrderRepositoryMock;

    @Mock
    private BarredEmployeeService barredEmployeeServiceMock;

    @InjectMocks
    private CustomerLocationService customerLocationService = new CustomerLocationServiceImpl();

    private CustomerLocationDTO customerLocationDTO;

    private CustomerLocationModel customerLocationModel;

    private WorkOrderModel workOrderModel;

    @Before
    public void initMocks() {
        setUpWorkOrderModel();
        setUpCustomerLocationDTO();
        setUpCustomerLocationModel();
    }

    private void setUpWorkOrderModel() {
        workOrderModel = new WorkOrderModel();
        workOrderModel.setId(11111l);
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

    private void setUpCustomerLocationModel() {
        customerLocationModel = new CustomerLocationModel();
        customerLocationModel.setId(1234l);
        AddressModel address = new AddressModel();
        address.setAddress("1234 Jupiter Street, Manila");
        address.setLatitude("0");
        address.setLongitude("0");
        address.setPostCode("1234");
        customerLocationModel.setAddress(address);
        List<BarredEmployeeModel> barredEmployees = Lists.newArrayList();
        BarredEmployeeModel barredEmployee = new BarredEmployeeModel();
        barredEmployee.setEmployeeId(1234l);
        barredEmployees.add(barredEmployee);
        customerLocationModel.setBarredEmployee(barredEmployees);
        customerLocationModel.setCreatedDate(new DateTime());
        List<EquipmentModel> equipments = Lists.newArrayList();
        EquipmentModel equipment = new EquipmentModel();
        equipment.setEquipmentName("Gun");
        equipments.add(equipment);
        customerLocationModel.setEquipments(equipments);
        customerLocationModel.setFloorPlan("Floor Plan");
        List<ModeTransportModel> modeTransports = Lists.newArrayList();
        customerLocationModel.setModeTransports(modeTransports);
        List<SkillsModel> skills = Lists.newArrayList();
        customerLocationModel.setSkills(skills);
        List<TaskModel> tasks = Lists.newArrayList();
        customerLocationModel.setTasks(tasks);
        CustomerModel customer = new CustomerModel();
        customer.setCustomerName("Juan Dela Cruz");
        customerLocationModel.setCustomer(customer );
        List<WorkOrderModel> workOrders = Lists.newArrayList();
        workOrders.add(workOrderModel);
        customerLocationModel.setWorkOrders(workOrders);
    }

    @Test
    public void testGetCustomerLocationDetails() {
        when(customerLocationRepositoryMock.findOne(1234l)).thenReturn(
                customerLocationModel);
        // mock barredEmployeeService
        List<BarredEmployeeDTO> barredEmployeeList = Lists.newArrayList();
        when(barredEmployeeServiceMock.getBarredEmployees(1234l)).thenReturn(
                barredEmployeeList);
        CustomerLocationDTO result = customerLocationService
                .getCustomerLocationDetails(1234l);
        verify(customerLocationRepositoryMock, times(1)).findOne(1234l);
        verify(barredEmployeeServiceMock, times(1)).getBarredEmployees(1234l);
        assertTrue(1234l == result.getId());
        assertEquals("Gun", result.getEquipments().get(0).getEquipmentName());
    }

    @Test
    public void testGetCustomerLocationByAddress() {
        List<CustomerLocationModel> repositoryResultList = Lists.newArrayList();
        repositoryResultList.add(customerLocationModel);
        when(customerLocationRepositoryMock.getCustomerLocationByAddress("1234 Jupiter Street, Manila"))
                .thenReturn(repositoryResultList);
        List<CustomerLocationDTO> results = customerLocationService.getCustomerLocationByAddress("1234 Jupiter Street, Manila");
        verify(customerLocationRepositoryMock, times(1)).getCustomerLocationByAddress("1234 Jupiter Street, Manila");
        assertEquals(1, results.size());
    }

    @Test
    public void testGetCustomerLocationByCustomerName() {
        List<CustomerLocationModel> repositoryResultList = Lists.newArrayList();
        repositoryResultList.add(customerLocationModel);
        when(customerLocationRepositoryMock.getCustomerLocationByCustomerName("Juan Dela Cruz"))
                .thenReturn(repositoryResultList);
        List<CustomerLocationDTO> results = customerLocationService.getCustomerLocationByCustomerName("Juan Dela Cruz");
        verify(customerLocationRepositoryMock, times(1)).getCustomerLocationByCustomerName("Juan Dela Cruz");
        assertEquals(1, results.size());
    }

    @Test
    @Ignore
    public void testSaveNewCustomerLocationDetails() {
        when(customerLocationRepositoryMock.save(customerLocationModel)).thenReturn(null);
        List<BarredEmployeeDTO> barredEmployeeList = Lists.newArrayList();
        BarredEmployeeDTO barredEmployee = new BarredEmployeeDTO();
        barredEmployee.setId(1111l);
        barredEmployeeList.add(barredEmployee );
        customerLocationModel.setId(1234l);

        when(customerLocationRepositoryMock.save(customerLocationModel)).thenReturn(customerLocationModel);
        customerLocationService.saveCustomerLocationDetails(customerLocationDTO);
        verify(customerLocationRepositoryMock, times(1)).save(any(CustomerLocationModel.class));
        doNothing().when(barredEmployeeServiceMock).saveBarredEmployees(barredEmployeeList, Mockito.anyLong());
    }

    @Test
    public void testAddExistingCustomerLocationToAWorkOrder() throws CustomerLocationException {
        when(workOrderRepositoryMock.findOne(11111l)).thenReturn(workOrderModel);
        when(customerLocationRepositoryMock.findOne(1234l)).thenReturn(
                customerLocationModel);
        when(customerLocationRepositoryMock.save(customerLocationModel)).thenReturn(null);
        List<BarredEmployeeDTO> barredEmployeeList = Lists.newArrayList();
        BarredEmployeeDTO barredEmployee = new BarredEmployeeDTO();
        barredEmployee.setId(1111l);
        barredEmployeeList.add(barredEmployee );
        customerLocationService.addExistingCustomerLocationToAWorkOrder(1234l, 11111l);
        verify(workOrderRepositoryMock, times(1)).findOne(11111l);
        verify(customerLocationRepositoryMock, times(1)).findOne(1234l);
        verify(customerLocationRepositoryMock, times(1)).save(any(CustomerLocationModel.class));
        doNothing().when(barredEmployeeServiceMock).saveBarredEmployees(barredEmployeeList, 1L);
    }

}
