package com.g4s.javelin.service.location.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.location.ContractModel;
import com.g4s.javelin.data.repository.location.ContractRepository;
import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.service.location.ContractService;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractServiceImpl implements ContractService {

    @Autowired
    @Lazy
    private ContractRepository contractRepository;

    @Override
    public void createNewContract(final ContractDTO contractDTO) {
        ContractModel contractModel = transformContractDTOtoModel(contractDTO);
        contractRepository.save(contractModel);
    }

    @Override
    public List<ContractDTO> getContractsDTO() {
        return getAllContractDTO();
    }

    @Override
    public List<ContractDTO> getContractByNumber(final String contractNumber) {
        List<ContractDTO> contractNumberSearchResults = new ArrayList<>();

        for (ContractDTO contractDTO : getAllContractDTO()) {
            if (contractDTO.getContractNumber().equals(contractNumber)) {
                contractNumberSearchResults.add(contractDTO);
            }
        }

        return contractNumberSearchResults;
    }

    @Override
    public List<ContractDTO> getContractByName(final String contractName) {
        List<ContractDTO> contractNameSearchResults = new ArrayList<>();

        for (ContractDTO contractDTO : getAllContractDTO()) {
            if (contractDTO.getContractName().equals(contractName) || contractDTO.getContractName().contains(contractName)) {
                contractNameSearchResults.add(contractDTO);
            }
        }

        return removeDuplicateElements(contractNameSearchResults);
    }

    private ContractModel transformContractDTOtoModel(final ContractDTO contractDTO) {
        ContractModel contract = new ContractModel();
        if (contractDTO != null) {
            contract.setContractName(contractDTO.getContractName());
        }
        return contract;
    }

    private ContractDTO transformContractModeltoDTO(final ContractModel contractModel) {
        ContractDTO contractDTO = new ContractDTO();
        if (contractModel != null) {
            contractDTO.setContractName(contractModel.getContractName());
        }
        return contractDTO;
    }

    private List<ContractDTO> transformContractModelListToDTO(final List<ContractModel> contractModelList) {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        for (ContractModel contractModel : contractModelList) {
            if (contractModel != null) {
                contractDTOList.add(transformContractModeltoDTO(contractModel));
            }
        }
        return contractDTOList;
    }

    private List<ContractDTO> removeDuplicateElements(final List<ContractDTO> contractDTOs) {
        List<ContractDTO> filteredElements = new ArrayList<>();

        for (ContractDTO contractDTO : contractDTOs) {
            if (!filteredElements.contains(contractDTO)) {
                filteredElements.add(contractDTO);
            }
        }
        return filteredElements;
    }

    /**
     * Mock Data for Contracts
     */
    private List<ContractDTO> getAllContractDTO() {
        List<ContractDTO> contractDTOList = new ArrayList<>();
        ContractDTO contractDTO = new ContractDTO();
        ContractDTO contractDTO1 = new ContractDTO();
        ContractDTO contractDTO2 = new ContractDTO();
        ContractDTO contractDTO3 = new ContractDTO();
        ContractDTO contractDTO4 = new ContractDTO();
        ContractDTO contractDTO5 = new ContractDTO();
        ContractDTO contractDTO6 = new ContractDTO();
        ContractDTO contractDTO7 = new ContractDTO();
        ContractDTO contractDTO8 = new ContractDTO();
        ContractDTO contractDTO9 = new ContractDTO();

        contractDTO.setContractNumber("1");
        contractDTO.setContractName("Contract of Contractual");
        contractDTOList.add(contractDTO);

        contractDTO1.setContractNumber("2");
        contractDTO1.setContractName("Contract of Peace and Order");
        contractDTOList.add(contractDTO1);

        contractDTO2.setContractNumber("3");
        contractDTO2.setContractName("Contract of Program Development");
        contractDTOList.add(contractDTO2);

        contractDTO3.setContractNumber("4");
        contractDTO3.setContractName("Contract of Online Health Service");
        contractDTOList.add(contractDTO3);

        contractDTO4.setContractNumber("5");
        contractDTO4.setContractName("Contract of Managerial Plan Development");
        contractDTOList.add(contractDTO4);

        contractDTO5.setContractNumber("6");
        contractDTO5.setContractName("Contract of Employment Regularization");
        contractDTOList.add(contractDTO5);

        contractDTO6.setContractNumber("7");
        contractDTO6.setContractName("Disclosure of Registered Company Name");
        contractDTOList.add(contractDTO6);

        contractDTO7.setContractNumber("8");
        contractDTO7.setContractName("Agreement for Termination of Computer Supply Arrangement");
        contractDTOList.add(contractDTO7);

        contractDTO8.setContractNumber("9");
        contractDTO8.setContractName("Distribution Fee Purchase Agreement");
        contractDTOList.add(contractDTO8);

        contractDTO9.setContractNumber("10");
        contractDTO9.setContractName("Management Rights Agreement");
        contractDTOList.add(contractDTO9);

        return contractDTOList;
    }

}
