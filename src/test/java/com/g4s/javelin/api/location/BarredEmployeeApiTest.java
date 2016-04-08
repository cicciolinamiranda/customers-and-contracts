package com.g4s.javelin.api.location;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.BarredEmployeeApi;
import com.g4s.javelin.data.model.location.CustomerLocationModel;
import com.g4s.javelin.dto.core.location.BarredEmployeeDTO;
import com.g4s.javelin.dto.core.location.BarredEmployeeListDTO;
import com.g4s.javelin.service.location.BarredEmployeeService;

/**
 * @author Jordan Duabe
 * @since 03/28/2016
 */
@RunWith(MockitoJUnitRunner.class)
public class BarredEmployeeApiTest {

    @Mock
    private BarredEmployeeService barredEmployeeService;

    @Mock
    private BarredEmployeeListDTO barredEmployeesList;

    @Mock
    private List<BarredEmployeeDTO> barredEmployees;

    @Mock
    private CustomerLocationModel customerLocation;

    @InjectMocks
    private BarredEmployeeApi barredEmployeeApi = new BarredEmployeeApi();

    @Test
    public void testSaveBarredEmployees() throws Exception {
        barredEmployeeApi.saveBarredEmployees(barredEmployeesList);
        Mockito.verify(barredEmployeeService, Mockito.times(1)).saveBarredEmployees(Mockito.anyListOf(
                BarredEmployeeDTO.class), Mockito.anyLong());
    }

    @Test
    public void testGetBarredEmployees() throws Exception {
        Mockito.when(barredEmployeeService.getBarredEmployees(Mockito.anyLong())).thenReturn(barredEmployees);
        assertNotNull(barredEmployeeApi.getBarredEmployees(1L));
    }
}