package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.ContractDTO;

/**
 * Created by apadilla on 4/8/16.
 */
public interface ContractService {

    ContractDTO saveContract(ContractDTO contractDTO);
    ContractDTO initializeContract();
    List<ContractDTO> getContractsDTO();
    List<ContractDTO> getContractByNumber(String contractNumber);
    List<ContractDTO> getContractByName(String contractName);
    List<ContractDTO> getContractByCustomerId(Long id);
    List<ContractDTO> searchContract(String searchTerm);
}
