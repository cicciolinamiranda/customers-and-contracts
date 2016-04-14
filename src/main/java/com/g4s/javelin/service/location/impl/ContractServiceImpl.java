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
        List<ContractDTO> contractDTOList = transformContractModelListToDTO(contractRepository.findAll());
        return contractDTOList;
    }

    @Override
    public List<ContractDTO> getContractByNumber(final String contractNumber) {
        List<ContractDTO> contractNumberSearchResults = new ArrayList<>();
        List<ContractDTO> allContracts = transformContractModelListToDTO(contractRepository.findAll());

        for (ContractDTO contractDTO : allContracts) {
            if (contractDTO != null) {
                if (contractDTO.getNumber().equals(contractNumber)) {
                    contractNumberSearchResults.add(contractDTO);
                }
            }
        }

        return contractNumberSearchResults;
    }

    @Override
    public List<ContractDTO> getContractByName(final String contractName) {
        List<ContractDTO> contractNameSearchResults = new ArrayList<>();
        List<ContractDTO> allContracts = transformContractModelListToDTO(contractRepository.findAll());

        for (ContractDTO contractDTO : allContracts) {
            if (contractDTO != null) {
                if (contractDTO.getName().equals(contractName) || contractDTO.getName().contains(contractName)) {
                    contractNameSearchResults.add(contractDTO);
                }
            }
        }

        return removeDuplicateElements(contractNameSearchResults);
    }

    private ContractModel transformContractDTOtoModel(final ContractDTO contractDTO) {
        ContractModel contract = new ContractModel();
        if (contractDTO != null) {
            contract.setCustomerNumber(contractDTO.getCustomerNumber());
            contract.setName(contractDTO.getName());
            contract.setNumber(contractDTO.getNumber());
            contract.setTitle(contractDTO.getTitle());
            contract.setStartDate(contractDTO.getStartDate());
            contract.setEndDate(contractDTO.getEndDate());
            contract.setReviewDate(contractDTO.getReviewDate());
            contract.setSignedDate(contractDTO.getSignedDate());
            contract.setWefDate(contractDTO.getWefDate());
        }
        return contract;
    }

    private ContractDTO transformContractModeltoDTO(final ContractModel contractModel) {
        ContractDTO contractDTO = new ContractDTO();
        if (contractModel != null) {
            contractDTO.setCustomerNumber(contractModel.getCustomerNumber());
            contractDTO.setName(contractModel.getName());
            contractDTO.setNumber(contractModel.getNumber());
            contractDTO.setTitle(contractModel.getTitle());
            contractDTO.setStartDate(contractModel.getStartDate());
            contractDTO.setEndDate(contractModel.getEndDate());
            contractDTO.setReviewDate(contractModel.getReviewDate());
            contractDTO.setSignedDate(contractModel.getSignedDate());
            contractDTO.setWefDate(contractModel.getWefDate());
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

}
