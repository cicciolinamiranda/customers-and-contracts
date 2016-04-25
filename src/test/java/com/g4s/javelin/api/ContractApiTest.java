package com.g4s.javelin.api;

import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.dto.core.location.CustomerDTO;
import com.g4s.javelin.service.location.ContractService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jalonzo on 4/25/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractApiTest {

    @Mock
    ContractService contractService;

    @Mock
    ContractDTO contractDTO, contractDTO2;

    @InjectMocks
    ContractApi contractApi = new ContractApi();

    @Before
    public void setUp(){
        contractDTO = new ContractDTO();
        contractDTO.setId(1L);
        contractDTO.setCustomerNumber("0000000");
        contractDTO.setNumber("999999");
        contractDTO.setName("Contract ABC");
        contractDTO.setTitle("Title ABC");
        contractDTO.setStartDate(DateTime.now());
        contractDTO.setEndDate(DateTime.now());
        contractDTO.setReviewDate(DateTime.now());
        contractDTO.setSignedDate(DateTime.now());
        contractDTO.setWefDate(DateTime.now());
        contractDTO.setStartDateStr(DateTime.now().toString());
        contractDTO.setStartDateStr(DateTime.now().toString());
        contractDTO.setReviewDateStr(DateTime.now().toString());
        contractDTO.setSignedDateStr(DateTime.now().toString());
        contractDTO.setWefDateStr(DateTime.now().toString());

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(2L);
        contractDTO.setCustomer(customerDTO);

        contractDTO2 = new ContractDTO();
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
        contractDTO2.setStartDateStr(DateTime.now().toString());
        contractDTO2.setStartDateStr(DateTime.now().toString());
        contractDTO2.setReviewDateStr(DateTime.now().toString());
        contractDTO2.setSignedDateStr(DateTime.now().toString());
        contractDTO2.setWefDateStr(DateTime.now().toString());

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setId(4L);
        contractDTO2.setCustomer(customerDTO2);

    }

    @Test
    public void testSaveContractDetails() throws Exception {
        Mockito.when(contractService.saveContract(contractDTO)).thenReturn(contractDTO);
        ContractDTO dto = contractApi.saveContractDetails(contractDTO);

        assertNotNull(dto);
        assertEquals(1L, dto.getId().longValue());
        assertEquals("0000000", dto.getCustomerNumber());
        assertEquals("999999", dto.getNumber());
        assertEquals("Contract ABC", dto.getName());
        assertEquals("Title ABC", dto.getTitle());
    }

    @Test
    public void testGetAllContracts() throws Exception {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contractDTOList.add(contractDTO);
        contractDTOList.add(contractDTO2);

        Mockito.when(contractService.getContractsDTO()).thenReturn(contractDTOList);
        List<ContractDTO> contractsList = contractApi.getAllContracts();

        assertTrue(!contractsList.isEmpty());
        assertEquals(2, contractsList.size());
    }

    @Test
    public void testGetContractByCustomerId() throws Exception {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contractDTOList.add(contractDTO);

        Mockito.when(contractService.getContractByCustomerId(4L)).thenReturn(contractDTOList);
        List<ContractDTO> contractsList = contractApi.getContractByCustomerId(4L);

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());
        assertEquals(2L, contractsList.get(0).getCustomer().getId().longValue());
    }

    @Test
    public void testGetContractByNumber() throws Exception {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contractDTOList.add(contractDTO2);

        Mockito.when(contractService.getContractByNumber("555555")).thenReturn(contractDTOList);
        List<ContractDTO> contractsList = contractApi.getContractByNumber("555555");

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());
        assertEquals("555555", contractsList.get(0).getNumber());
    }

    @Test
    public void testGetContractByName() throws Exception {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contractDTOList.add(contractDTO);

        Mockito.when(contractService.getContractByName("Contract ABC")).thenReturn(contractDTOList);
        List<ContractDTO> contractsList = contractApi.getContractByName("Contract ABC");

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());
        assertEquals("Contract ABC", contractsList.get(0).getName());

    }

    @Test
    public void testSearchAllContracts() throws Exception {
        String searchTerm = "DEF";
        List<ContractDTO> contractDTOList = new ArrayList<>();
        contractDTOList.add(contractDTO2);

        Mockito.when(contractService.searchContract(searchTerm)).thenReturn(contractDTOList);
        List<ContractDTO> contractsList  = contractApi.searchAllContracts(searchTerm);

        assertTrue(!contractsList.isEmpty());
        assertEquals(1, contractsList.size());
        assertEquals("Contract DEF", contractsList.get(0).getName());
    }
}