package com.g4s.javelin.api.location;

import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * @author Jordan Duabe
 * @since 03/28/2016
 */
@RunWith(MockitoJUnitRunner.class)
public class BarredEmployeeApiTest extends TestCase {

    @Mock
    private BarredEmployeeService barredEmployeeService;

    @Mock
    private List<BarredEmployeeDTO> barredEmployees;

    @Mock
    private CustomerLocationModel customerLocation;

    @InjectMocks
    private BarredEmployeeApi barredEmployeeApi = new BarredEmployeeApi();

    @Test
    public void testSaveBarredEmployees() throws Exception {
        barredEmployeeApi.saveBarredEmployees(barredEmployees, customerLocation);
        Mockito.verify(barredEmployeeService, Mockito.times(1)).saveBarredEmployees(Mockito.anyListOf(
                BarredEmployeeDTO.class), Mockito.any(CustomerLocationModel.class));
    }

    @Test
    public void testGetBarredEmployees() throws Exception {
        List<BarredEmployeeDTO> barredEmployees = barredEmployeeApi.getBarredEmployees(1L);
        Mockito.when(barredEmployeeService.getBarredEmployees(Mockito.anyLong())).thenReturn(barredEmployees);
        assertNotNull(barredEmployees);
    }
}