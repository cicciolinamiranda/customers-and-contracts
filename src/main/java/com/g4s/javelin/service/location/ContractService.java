package com.g4s.javelin.service.location;

import java.util.List;

import com.g4s.javelin.dto.core.location.ContractDTO;

/**
 * Created by apadilla on 4/8/16.
 */
public interface ContractService {

    void createNewContract(ContractDTO contractDTO);
    List<ContractDTO> getContractsDTO();
    List<ContractDTO> getContractByNumber(Long contractNumber);
    List<ContractDTO> getContractByName(String contractName);
}
