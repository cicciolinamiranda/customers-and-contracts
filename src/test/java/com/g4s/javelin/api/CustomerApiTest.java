package com.g4s.javelin.api;

import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jalonzo on 4/18/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerApiTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerApi customerApi = new CustomerApi();

    CustomerDTO customerDTO,customerDTO2, customerDTO3;

    @Before
    public void setUp() throws Exception {
        customerDTO = new CustomerDTO();
        customerDTO2 = new CustomerDTO();
        customerDTO3 = new CustomerDTO();

        customerDTO.setId(1L);
        customerDTO.setCustomerNumber("111111");
        customerDTO.setCustomerCode("222222");
        customerDTO.setManualCustomerCode("333333");
        customerDTO.setVatNumber("444444");
        customerDTO.setDunsNumber("555555");
        customerDTO.setCustomerName("John Smith");

        customerDTO2.setId(2L);
        customerDTO2.setCustomerNumber("666666");
        customerDTO2.setCustomerCode("777777");
        customerDTO2.setManualCustomerCode("888888");
        customerDTO2.setVatNumber("999999");
        customerDTO2.setDunsNumber("000000");
        customerDTO2.setCustomerName("John Smith");

        customerDTO3.setId(2L);
        customerDTO3.setCustomerNumber("000111");
        customerDTO3.setCustomerCode("222333");
        customerDTO3.setManualCustomerCode("444555");
        customerDTO3.setVatNumber("666777");
        customerDTO3.setDunsNumber("888999");
        customerDTO3.setCustomerName("Jane Doe");
    }

    @Test
    public void testGetCustomerByCustomerNumber() throws Exception {
        Mockito.when(customerService.getCustomerByCustomerNumber("111111")).thenReturn(customerDTO);
        CustomerDTO dto = customerApi.getCustomerByCustomerNumber("111111");

        assertEquals("111111", dto.getCustomerNumber());
        assertNotNull(dto);
    }

    @Test
    public void testGetCustomerByCustomerName() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO);
        customerDTOList.add(customerDTO2);

        Mockito.when(customerService.getCustomerByCustomerName("John Smith")).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerByCustomerName("John Smith");

        assertTrue(!customerList.isEmpty());
        assertEquals(2, customerList.size());
        assertEquals("John Smith", customerList.get(0).getCustomerName());
        assertEquals("John Smith", customerList.get(1).getCustomerName());
    }

    @Test
    public void testGetCustomerByCustomerCode() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO3);

        Mockito.when(customerService.getCustomerByCustomerCode("222333")).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerByCustomerCode("222333");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("222333", customerList.get(0).getCustomerCode());
    }

    @Test
    public void testGetCustomerByManualCustomerCode() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO2);

        Mockito.when(customerService.getCustomerByManualCustomerCode("888888")).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerByManualCustomerCode("888888");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("888888", customerList.get(0).getManualCustomerCode());
    }

    @Test
    public void testGetCustomerByVatNumber() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO);

        Mockito.when(customerService.getCustomerByVatNumber("444444")).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerByVatNumber("444444");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("444444", customerList.get(0).getVatNumber());
    }

    @Test
    public void testGetCustomerByDunsNumber() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO);

        Mockito.when(customerService.getCustomerByDunsNumber("555555")).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerByDunsNumber("555555");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("555555", customerList.get(0).getDunsNumber());
    }

    @Test
    public void testGetCustomerList() throws Exception {
        List<CustomerDTO> customerDTOList  = new ArrayList<>();
        customerDTOList.add(customerDTO);
        customerDTOList.add(customerDTO2);
        customerDTOList.add(customerDTO3);

        Mockito.when(customerService.getCustomerList()).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.getCustomerList();

        assertTrue(!customerList.isEmpty());
        assertEquals(3, customerList.size());
    }

    @Test
    public void testSearchCustomer() throws Exception {
        String searchTerm = "John";
        List<CustomerDTO> customerDTOList  = new ArrayList<>();
        customerDTOList.add(customerDTO);
        customerDTOList.add(customerDTO2);

        Mockito.when(customerService.searchAllCustomers(searchTerm)).thenReturn(customerDTOList);
        List<CustomerDTO> customerList = customerApi.searchCustomer(searchTerm);

        assertTrue(!customerList.isEmpty());
        assertEquals(2, customerList.size());
    }
}