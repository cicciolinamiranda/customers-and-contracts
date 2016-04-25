package com.g4s.javelin.service.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

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
import com.g4s.javelin.data.model.masterfile.MasterfileModel;
import com.g4s.javelin.data.model.masterfile.TaskModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.data.repository.workorder.WorkOrderRepository;
import com.g4s.javelin.dto.core.location.AddressDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;
import com.g4s.javelin.dto.core.masterfile.ModeTransportDTO;
import com.g4s.javelin.dto.core.masterfile.TaskDTO;
import com.g4s.javelin.enums.StatusEnum;
import com.g4s.javelin.exception.CustomerLocationException;
import com.g4s.javelin.service.location.impl.CustomerLocationServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;
import com.google.appengine.repackaged.com.google.api.client.util.Sets;

@RunWith(MockitoJUnitRunner.class)
public class CustomerLocationServiceTest {

    @Mock
    private CustomerLocationRepository customerLocationRepositoryMock;

    @Mock
    private WorkOrderRepository workOrderRepositoryMock;

    @Mock
    private BarredEmployeeService barredEmployeeServiceMock;

    @Mock
    private SiteLocationService siteLocationService;

    @InjectMocks
    private CustomerLocationService customerLocationService = new CustomerLocationServiceImpl();

    @Mock
    private LocationMasterfileAssociationService masterfileAssociationService;

    private CustomerLocationDTO customerLocationDTO;

    private CustomerLocationModel customerLocationModel;

    private WorkOrderModel workOrderModel;

    private List<EquipmentDTO> equipments = Lists.newArrayList();

    private List<ModeTransportDTO> modeTransports = Lists.newArrayList();

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
        equipmentDTO.setName("Gun");
        equipments.add(equipmentDTO);
        customerLocationDTO.setEquipments(equipments);
        customerLocationDTO.setFloorPlan("Floor Plan");
        CustomerDTO customer = new CustomerDTO();
        customer.setCustomerName("Juan Dela Cruz");
        customerLocationDTO.setModeOfTransports(modeTransports);
        List<MasterfileDTO> skills = Lists.newArrayList();
        customerLocationDTO.setSkills(skills);
        List<TaskDTO> tasks = Lists.newArrayList();
        customerLocationDTO.setTasks(tasks);
        customerLocationDTO.setCustomer(customer);
        customerLocationDTO.setStartDateStr("05/12/2016");
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
        Set<BarredEmployeeModel> barredEmployees = Sets.newHashSet();
        BarredEmployeeModel barredEmployee = new BarredEmployeeModel();
        barredEmployee.setEmployeeId(1234l);
        barredEmployees.add(barredEmployee);
        customerLocationModel.setBarredEmployee(barredEmployees);
        //customerLocationModel.setCreatedDate(new DateTime());
        List<MasterfileModel> equipments = Lists.newArrayList();
        MasterfileModel equipment = new MasterfileModel();
        equipment.setName("Gun");
        equipments.add(equipment);
        customerLocationModel.setFloorPlan("Floor Plan");
        Set<MasterfileModel> modeTransports = Sets.newHashSet();
        Set<MasterfileModel> skills = Sets.newHashSet();
        customerLocationModel.setSkills(skills);
        Set<TaskModel> tasks = Sets.newHashSet();
        customerLocationModel.setTasks(tasks);
        CustomerModel customer = new CustomerModel();
        customer.setCustomerName("Juan Dela Cruz");
        customerLocationModel.setCustomer(customer );
        Set<WorkOrderModel> workOrders = Sets.newHashSet();
        workOrders.add(workOrderModel);
        customerLocationModel.setWorkOrders(workOrders);
        customerLocationModel.setStatus(StatusEnum.INPROGRESS);
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
    }

    @Test
    public void testGetCustomerLocationByAddress() {
        List<CustomerLocationModel> repositoryResultList = Lists.newArrayList();
        repositoryResultList.add(customerLocationModel);
        when(customerLocationRepositoryMock.findByAddressAddressContainingIgnoreCase("1234 Jupiter Street, Manila"))
                .thenReturn(repositoryResultList);
        List<CustomerLocationDTO> results = customerLocationService.getCustomerLocationByAddress("1234 Jupiter Street, Manila");
        verify(customerLocationRepositoryMock, times(1)).findByAddressAddressContainingIgnoreCase("1234 Jupiter Street, Manila");
        assertEquals(1, results.size());
    }

    @Test
    public void testGetCustomerLocationByCustomerName() {
        List<CustomerLocationModel> repositoryResultList = Lists.newArrayList();
        repositoryResultList.add(customerLocationModel);
        when(customerLocationRepositoryMock.findByCustomerCustomerNameContainingIgnoreCase("Juan Dela Cruz"))
                .thenReturn(repositoryResultList);
        List<CustomerLocationDTO> results = customerLocationService.getCustomerLocationByCustomerName("Juan Dela Cruz");
        verify(customerLocationRepositoryMock, times(1)).findByCustomerCustomerNameContainingIgnoreCase("Juan Dela Cruz");
        assertEquals(1, results.size());
    }

    @Ignore
    @Test
    public void testSaveNewCustomerLocationDetails() throws Exception {
        CustomerLocationModel model = new CustomerLocationModel();
        model.setId(1L);
        when(workOrderRepositoryMock.findOne(11111l)).thenReturn(workOrderModel);
        when(customerLocationRepositoryMock.save(customerLocationModel)).thenReturn(model);
        customerLocationService.saveCustomerLocationDetails(customerLocationDTO);
        verify(customerLocationRepositoryMock, times(1)).save(any(CustomerLocationModel.class));
        doNothing().when(barredEmployeeServiceMock).saveBarredEmployees(customerLocationDTO.getBarredEmployees(), 1L);

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
        doNothing().when(barredEmployeeServiceMock).saveBarredEmployees(barredEmployeeList, 1L	);
    }
    
    @Test
    public void updateCustomerLocationStatus() throws CustomerLocationException {    	
        //for call to getCustomerLocationDetails()
    	when(customerLocationRepositoryMock.findOne(1L)).thenReturn(customerLocationModel);
    	 // mock barredEmployeeService
        List<BarredEmployeeDTO> barredEmployeeList = Lists.newArrayList();
        when(barredEmployeeServiceMock.getBarredEmployees(1L)).thenReturn(
                barredEmployeeList);
        customerLocationService.updateCustomerLocationStatus(1L,"IN_PROGRESS", "reason", "127.0.0.0");
        Mockito.verify(customerLocationRepositoryMock, Mockito.times(1)).updateStatus(1L, StatusEnum.INPROGRESS);
    }

}
