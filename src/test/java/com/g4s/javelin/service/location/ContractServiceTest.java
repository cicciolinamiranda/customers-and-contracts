package com.g4s.javelin.service.location;

import com.g4s.javelin.data.model.location.ContractModel;
import com.g4s.javelin.data.model.location.CustomerModel;
import com.g4s.javelin.data.repository.location.ContractRepository;
import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.impl.ContractServiceImpl;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jalonzo on 4/25/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractServiceTest extends TestCase {

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    ContractService contractService = new ContractServiceImpl();

    ContractDTO contractDTO, contractDTO2;
    ContractModel contractModel, contractModel2;

    @Before
    public void setUp(){
        setupContractDTO();
        setupContractModel();
    }

    private void setupContractDTO() {
        contractDTO = new ContractDTO();
        contractDTO.setId(1L);
        contractDTO.setCustomerNumber("0000000");
        contractDTO.setNumber("999999");
        contractDTO.setName("Contract ABC");
        contractDTO.setTitle("Title ABC");
        DateTime date= new DateTime();
        contractDTO.setStartDate(date);
        contractDTO.setEndDate(date.plusDays(3));
        contractDTO.setReviewDate(date);
        contractDTO.setSignedDate(date);
        contractDTO.setWefDate(date);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(2L);
        contractDTO.setCustomer(customerDTO);

        contractDTO2 = new ContractDTO();
        contractDTO2.setId(3L);
        contractDTO2.setCustomerNumber("444444");
        contractDTO2.setNumber("555555");
        contractDTO2.setName("Contract DEF");
        contractDTO2.setTitle("Title DEF");
        contractDTO2.setStartDate(DateTime.now());
        contractDTO2.setEndDate(DateTime.now().plusDays(3));
        contractDTO2.setReviewDate(DateTime.now());
        contractDTO2.setSignedDate(DateTime.now());
        contractDTO2.setWefDate(DateTime.now());

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(4L);
        contractDTO2.setCustomer(customerDTO2);


    }

    private void setupContractModel() {
        contractModel = new ContractModel();
        contractModel.setId(1L);
        contractModel.setCustomerNumber("0000000");
        contractModel.setNumber("999999");
        contractModel.setName("Contract ABC");
        contractModel.setTitle("Title ABC");

        DateTime date= new DateTime();
        contractModel.setStartDate(date);
        contractModel.setEndDate(date);
        contractModel.setReviewDate(date);
        contractModel.setSignedDate(date);
        contractModel.setWefDate(date);

        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(2L);
        contractModel.setCustomer(customerModel);


        contractModel2 = new ContractModel();
        contractModel2.setId(3L);
        contractModel2.setCustomerNumber("444444");
        contractModel2.setNumber("555555");
        contractModel2.setName("Contract DEF");
        contractModel2.setTitle("Title DEF");
        contractModel2.setStartDate(DateTime.now());
        contractModel2.setEndDate(DateTime.now().plusDays(3));
        contractModel2.setReviewDate(DateTime.now());
        contractModel2.setSignedDate(DateTime.now());
        contractModel2.setWefDate(DateTime.now());

        CustomerModel customerModel2 = new CustomerModel();
        customerModel.setId(4L);
        contractModel2.setCustomer(customerModel2);
    }

    @Test
    public void testSaveContract() throws Exception {
        Mockito.when(contractRepository.save(contractModel)).thenReturn(contractModel);
        ContractDTO dto = contractService.saveContract(contractDTO);

        Mockito.verify(contractRepository, Mockito.times(1)).save(Mockito.any(ContractModel.class));
        assertEquals(1L, dto.getId().longValue());
        assertEquals("999999", dto.getNumber());
        assertEquals("0000000", dto.getCustomerNumber());
        assertEquals("Contract ABC", dto.getName());
        assertEquals("Title ABC", dto.getTitle());

    }

    @Test
    public void testGetContractsDTO() throws Exception {
        List<ContractModel> contractModelList = new ArrayList<>();
        contractModelList.add(contractModel);
        contractModelList.add(contractModel2);

        Mockito.when(contractRepository.findAll()).thenReturn(contractModelList);
        List<ContractDTO> contractsList = contractService.getContractsDTO();
        Mockito.verify(contractRepository, Mockito.times(1)).findAll();

        assertTrue(!contractsList.isEmpty());
        assertEquals(2, contractsList.size());

        assertEquals(1L, contractsList.get(0).getId().longValue());
        assertEquals("999999", contractsList.get(0).getNumber());
        assertEquals("0000000", contractsList.get(0).getCustomerNumber());
        assertEquals("Contract ABC", contractsList.get(0).getName());
        assertEquals("Title ABC", contractsList.get(0).getTitle());

        assertEquals(3L, contractsList.get(1).getId().longValue());
        assertEquals("555555", contractsList.get(1).getNumber());
        assertEquals("444444", contractsList.get(1).getCustomerNumber());
        assertEquals("Contract DEF", contractsList.get(1).getName());
        assertEquals("Title DEF", contractsList.get(1).getTitle());
    }

    @Test
    public void testGetContractByNumber() throws Exception {
        List<ContractModel> contractModelList = new ArrayList<>();
        contractModelList.add(contractModel);

        Mockito.when(contractRepository.findByNumber("0000000")).thenReturn(contractModelList);
        List<ContractDTO> contractsList = contractService.getContractByNumber("0000000");
        Mockito.verify(contractRepository, Mockito.times(1)).findByNumber("0000000");

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());

        assertEquals(1L, contractsList.get(0).getId().longValue());
        assertEquals("999999", contractsList.get(0).getNumber());
        assertEquals("0000000", contractsList.get(0).getCustomerNumber());
        assertEquals("Contract ABC", contractsList.get(0).getName());
        assertEquals("Title ABC", contractsList.get(0).getTitle());

    }

    @Test
    public void testGetContractByName() throws Exception {
        List<ContractModel> contractModelList = new ArrayList<>();
        contractModelList.add(contractModel2);

        Mockito.when(contractRepository.findByName("Contract DEF")).thenReturn(contractModelList);
        List<ContractDTO> contractsList = contractService.getContractByName("Contract DEF");
        Mockito.verify(contractRepository, Mockito.times(1)).findByName("Contract DEF");

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());

        assertEquals(3L, contractsList.get(0).getId().longValue());
        assertEquals("555555", contractsList.get(0).getNumber());
        assertEquals("444444", contractsList.get(0).getCustomerNumber());
        assertEquals("Contract DEF", contractsList.get(0).getName());
        assertEquals("Title DEF", contractsList.get(0).getTitle());
    }

    @Test
    public void testGetContractByCustomerId() throws Exception {
        List<ContractModel> contractModelList = new ArrayList<>();
        contractModelList.add(contractModel2);

        Mockito.when(contractRepository.findByCustomerId(4L)).thenReturn(contractModelList);
        List<ContractDTO> contractsList = contractService.getContractByCustomerId(4L);
        Mockito.verify(contractRepository, Mockito.times(1)).findByCustomerId(4L);

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());

        assertEquals(3L, contractsList.get(0).getId().longValue());
        assertEquals("555555", contractsList.get(0).getNumber());
        assertEquals("444444", contractsList.get(0).getCustomerNumber());
        assertEquals("Contract DEF", contractsList.get(0).getName());
        assertEquals("Title DEF", contractsList.get(0).getTitle());
    }

    @Test
    public void testSearchContract() throws Exception {
        String searchTerm = "ABC";

        List<ContractModel> contractModelList = new ArrayList<>();
        contractModelList.add(contractModel);

        Mockito.when(contractRepository.findBySearchTerm(Mockito.anyLong(), Mockito.anyString())).thenReturn(contractModelList);
        List<ContractDTO> contractsList = contractService.searchContract(searchTerm);

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());

        assertEquals(1L, contractsList.get(0).getId().longValue());
        assertEquals("999999", contractsList.get(0).getNumber());
        assertEquals("0000000", contractsList.get(0).getCustomerNumber());
        assertEquals("Contract ABC", contractsList.get(0).getName());
        assertEquals("Title ABC", contractsList.get(0).getTitle());
    }
}