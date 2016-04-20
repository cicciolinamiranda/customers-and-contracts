package com.g4s.javelin.service.location;

import com.g4s.javelin.data.model.location.CustomerModel;
import com.g4s.javelin.data.repository.location.CustomerRepository;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by jalonzo on 4/20/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    private CustomerModel customerModel, customerModel2;

    @Before
    public void setUp() throws Exception {
        setupCustomerModel();
    }

    private void setupCustomerModel() {
        customerModel = new CustomerModel();
        customerModel.setId(1L);
        customerModel.setCustomerNumber("123456");
        customerModel.setCustomerName("Sherlock Holmes");
        customerModel.setCustomerCode("CD-123");
        customerModel.setDunsNumber("111000");
        customerModel.setAccountNumber("883134");
        customerModel.setAccountDescription("This is the account description");
        customerModel.setManualCustomerCode("454545");
        customerModel.setVatNumber("908070");
        customerModel.setAddressLine1("221B Baker Street");
        customerModel.setCity("Westminster");

        customerModel2 = new CustomerModel();
        customerModel2.setId(2L);
        customerModel2.setCustomerNumber("098765");
        customerModel2.setCustomerName("John Watson");
        customerModel2.setCustomerCode("CD-123");
        customerModel2.setDunsNumber("000111");
        customerModel2.setAccountNumber("090908");
        customerModel2.setAccountDescription("Account Description");
        customerModel2.setManualCustomerCode("670000");
        customerModel2.setVatNumber("999771");
        customerModel2.setAddressLine1("221B Baker Street");
        customerModel2.setCity("Westminster");

    }

    @Test
    public void testGetCustomerByCustomerNumber() throws Exception {
        Mockito.when(customerRepository.findByCustomerNumber("123456")).thenReturn(customerModel);
        CustomerDTO dto = customerService.getCustomerByCustomerNumber("123456");

        assertNotNull(dto);
        assertTrue(1L == dto.getId());
        assertEquals("123456", dto.getCustomerNumber());
        assertEquals("Sherlock Holmes", dto.getCustomerName());

        Mockito.verify(customerRepository, Mockito.times(1)).findByCustomerNumber("123456");
    }

    @Test
    public void testGetCustomerByCustomerName() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel2);

        Mockito.when(customerRepository.findByCustomerName("John Watson")).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.getCustomerByCustomerName("John Watson");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("098765", customerList.get(0).getCustomerNumber());
        assertEquals("John Watson", customerList.get(0).getCustomerName());

        Mockito.verify(customerRepository, Mockito.times(1)).findByCustomerName("John Watson");
    }

    @Test
    public void testGetCustomerByCustomerCode() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel);
        customerModelList.add(customerModel2);

        Mockito.when(customerRepository.findByCustomerCode("CD-123")).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.getCustomerByCustomerCode("CD-123");

        assertTrue(!customerList.isEmpty());

        assertEquals(2, customerList.size());
        assertEquals("123456", customerList.get(0).getCustomerNumber());
        assertEquals("Sherlock Holmes", customerList.get(0).getCustomerName());
        assertEquals("CD-123", customerList.get(0).getCustomerCode());

        assertEquals("098765", customerList.get(1).getCustomerNumber());
        assertEquals("John Watson", customerList.get(1).getCustomerName());
        assertEquals("CD-123", customerList.get(1).getCustomerCode());

    }

    @Test
    public void testGetCustomerByManualCustomerCode() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel);

        Mockito.when(customerRepository.findByManualCustomerCode("454545")).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.getCustomerByManualCustomerCode("454545");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("123456", customerList.get(0).getCustomerNumber());
        assertEquals("Sherlock Holmes", customerList.get(0).getCustomerName());
        assertEquals("454545", customerList.get(0).getManualCustomerCode());

    }

    @Test
    public void testGetCustomerByVatNumber() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel2);

        Mockito.when(customerRepository.findByVatNumber("999771")).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.getCustomerByVatNumber("999771");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("098765", customerList.get(0).getCustomerNumber());
        assertEquals("John Watson", customerList.get(0).getCustomerName());
        assertEquals("999771", customerList.get(0).getVatNumber());
    }

    @Test
    public void testGetCustomerByDunsNumber() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel);

        Mockito.when(customerRepository.findByDunsNumber("111000")).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.getCustomerByDunsNumber("111000");

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("123456", customerList.get(0).getCustomerNumber());
        assertEquals("Sherlock Holmes", customerList.get(0).getCustomerName());
        assertEquals("111000", customerList.get(0).getDunsNumber());
    }

    @Test
    public void testSearchAllCustomers() throws Exception {
        String searchTerm = "Watson";
        String searchTermDigit = "670000";
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel2);

        Mockito.when(customerRepository.searchCustomer(Mockito.anyLong(), Mockito.anyString())).thenReturn(customerModelList);
        List<CustomerDTO> customerList = customerService.searchAllCustomers(searchTerm);

        assertTrue(!customerList.isEmpty());
        assertEquals(1, customerList.size());
        assertEquals("John Watson", customerList.get(0).getCustomerName());

        customerList = customerService.searchAllCustomers(searchTermDigit);
        assertEquals(1, customerList.size());
    }

    @Test
    public void testGetCustomerList() throws Exception {
        List<CustomerModel> customerModelList = new ArrayList<CustomerModel>();
        customerModelList.add(customerModel);
        customerModelList.add(customerModel2);

        Mockito.when(customerRepository.findAll()).thenReturn(customerModelList);

        List<CustomerDTO> customerList = customerService.getCustomerList();
        assertTrue(!customerList.isEmpty());
        assertEquals(2, customerList.size());

        assertEquals("Sherlock Holmes", customerList.get(0).getCustomerName());
        assertEquals("John Watson", customerList.get(1).getCustomerName());

    }
}