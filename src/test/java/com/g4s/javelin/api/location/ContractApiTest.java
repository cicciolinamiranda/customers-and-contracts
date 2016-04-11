package com.g4s.javelin.api.location;

import com.g4s.javelin.api.ContractApi;
import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.service.location.ContractService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

/**
 * Created by apadilla on 4/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractApiTest {

    @Mock
    private ContractService contractService;

    @Mock
    private ContractDTO contractDTO;

    @Mock
    private List<ContractDTO> contractDTOList;

    @InjectMocks
    private ContractApi contractApi = new ContractApi();

    @Before
    public void initMocks() {
        setupContractDTO();
    }

    private void setupContractDTO() {

    }

    @Test
    public void testGetContracts() throws Exception {
        Mockito.when(contractService.getContractsDTO()).thenReturn(contractDTOList);
    }
}
