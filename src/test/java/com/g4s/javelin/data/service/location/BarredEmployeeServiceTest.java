package com.g4s.javelin.data.service.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.data.model.location.BarredEmployeeModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.data.repository.location.BarredEmployeeRepository;
import com.g4s.javelin.data.repository.location.CustomerLocationRepository;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;
import com.g4s.javelin.service.location.impl.BarredEmployeeServiceImpl;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

@RunWith(MockitoJUnitRunner.class)
public class BarredEmployeeServiceTest {

    @Mock
    private BarredEmployeeRepository barredEmployeeRepositoryMock;

    @Mock
    private CustomerLocationRepository customerLocationRepository;

    @InjectMocks
    private BarredEmployeeService barredEmployeeService = new BarredEmployeeServiceImpl();

    private BarredEmployeeModel barredEmployeeModel;

    private BarredEmployeeDTO barredEmployeeDTO;

    @Before
    public void initMocks() {
        setUpEmployeeModel();
        setUpEmployeeDTO();
    }

    private void setUpEmployeeModel() {
        barredEmployeeModel = new BarredEmployeeModel();
        barredEmployeeModel.setId(1234l);
        barredEmployeeModel.setEmployeeId(1234l);
        barredEmployeeModel.setStartDate(new DateTime());
        barredEmployeeModel.setEndDate(new DateTime().plusDays(3));
    }

    private void setUpEmployeeDTO() {
        barredEmployeeDTO = new BarredEmployeeDTO();
        barredEmployeeDTO.setStartDate(new DateTime());
        barredEmployeeDTO.setEndDate(new DateTime().plusDays(3));
    }

    @Test
    public void testSaveBarredEmployees() {
        List<BarredEmployeeModel> barredEmployees = Lists.newArrayList();
        barredEmployees.add(barredEmployeeModel);
//        when(barredEmployeeRepositoryMock.save(barredEmployees));
        CustomerLocationModel customerLocation = new CustomerLocationModel();
        when(customerLocationRepository.findOne(Mockito.anyLong())).thenReturn(new CustomerLocationModel());
        List<BarredEmployeeDTO> list = Lists.newArrayList();
        list.add(barredEmployeeDTO);
        
        barredEmployeeService.saveBarredEmployees(list, 1L);
//        verify(barredEmployeeRepositoryMock, times(1)).save(barredEmployees);
    }

    @Test
    public void testgetBarredEmployees() {
        List<BarredEmployeeModel> expectedList = Lists.newArrayList();
        expectedList.add(barredEmployeeModel);
        when(barredEmployeeRepositoryMock.getBarredEmployeeByCustomerLocation(1234l)).thenReturn(expectedList);
        List<BarredEmployeeDTO> results = barredEmployeeService.getBarredEmployees(1234l);
        verify(barredEmployeeRepositoryMock, times(1)).getBarredEmployeeByCustomerLocation(1234l);
        assertEquals(1, results.size());
        assertTrue(1234l==results.get(0).getEmployeeId());
    }
}
