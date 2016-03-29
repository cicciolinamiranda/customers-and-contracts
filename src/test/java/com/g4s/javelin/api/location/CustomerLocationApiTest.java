package com.g4s.javelin.api.location;

import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.service.location.CustomerLocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

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
}