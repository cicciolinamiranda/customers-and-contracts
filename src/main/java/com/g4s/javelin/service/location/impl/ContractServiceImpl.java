package com.g4s.javelin.service.location.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
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
            contract.setContractNumber(contractDTO.getContractNumber());
            contract.setContractTitle(contractDTO.getContractTitle());
            contract.setContractStartDate(contractDTO.getContractStartDate());
            contract.setContractEndDate(contractDTO.getContractEndDate());
            contract.setContractReviewDate(contractDTO.getContractReviewDate());
            contract.setContractSignedDate(contractDTO.getContractSignedDate());
            contract.setContractWEFDate(contractDTO.getContractWEFDate());
        }
        return contract;
    }

    private ContractDTO transformContractModeltoDTO(final ContractModel contractModel) {
        ContractDTO contractDTO = new ContractDTO();
        if (contractModel != null) {
            contractDTO.setContractName(contractModel.getContractName());
            contractDTO.setContractNumber(contractModel.getContractNumber());
            contractDTO.setContractTitle(contractModel.getContractTitle());
            contractDTO.setContractStartDate(contractModel.getContractStartDate());
            contractDTO.setContractEndDate(contractModel.getContractEndDate());
            contractDTO.setContractReviewDate(contractModel.getContractReviewDate());
            contractDTO.setContractSignedDate(contractModel.getContractSignedDate());
            contractDTO.setContractWEFDate(contractModel.getContractWEFDate());
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

        //Contract number constants
        final String contractNumber1 = "1";
        final String contractNumber2 = "2";
        final String contractNumber3 = "3";
        final String contractNumber4 = "4";
        final String contractNumber5 = "5";

        //Dates
        final int year = 2017;
        final int month = 5;
        final int day = 20;
        DateTime dt = new DateTime();

        contractDTO.setContractNumber(contractNumber1);
        contractDTO.setContractTitle("Contract of Associates");
        contractDTO.setContractName("Contract of Contractual");
        contractDTO.setContractStartDate(dt);
        contractDTO.setContractEndDate(dt.withDate(year, month, day));
        contractDTO.setContractReviewDate(dt.withDate(year, month, day));
        contractDTO.setContractSignedDate(dt.withDate(year, month, day));
        contractDTO.setContractWEFDate(dt.withDate(year, month, day));
        contractDTOList.add(contractDTO);

        contractDTO1.setContractNumber(contractNumber2);
        contractDTO1.setContractTitle("Peace and Order in Land and Sea Agreement");
        contractDTO1.setContractName("Contract of Peace and Order");
        contractDTO1.setContractStartDate(dt);
        contractDTO1.setContractEndDate(dt.withDate(year, month, day));
        contractDTO1.setContractReviewDate(dt.withDate(year, month, day));
        contractDTO1.setContractSignedDate(dt.withDate(year, month, day));
        contractDTO1.setContractWEFDate(dt.withDate(year, month, day));
        contractDTOList.add(contractDTO1);

        contractDTO2.setContractNumber(contractNumber3);
        contractDTO2.setContractName("Contract of Program Development");
        contractDTO2.setContractTitle("Development for Educational Curriculum on Secondary Levels");
        contractDTO2.setContractStartDate(dt);
        contractDTO2.setContractEndDate(dt.withDate(year, month, day));
        contractDTO2.setContractReviewDate(dt.withDate(year, month, day));
        contractDTO2.setContractSignedDate(dt.withDate(year, month, day));
        contractDTO2.setContractWEFDate(dt.withDate(year, month, day));
        contractDTOList.add(contractDTO2);

        contractDTO3.setContractNumber(contractNumber4);
        contractDTO3.setContractName("Contract of Online Health Service");
        contractDTO3.setContractTitle("Program Health Service for Filipino Citizens");
        contractDTO3.setContractStartDate(dt);
        contractDTO3.setContractEndDate(dt.withDate(year, month, day));
        contractDTO3.setContractReviewDate(dt.withDate(year, month, day));
        contractDTO3.setContractSignedDate(dt.withDate(year, month, day));
        contractDTO3.setContractWEFDate(dt.withDate(year, month, day));
        contractDTOList.add(contractDTO3);

        contractDTO4.setContractNumber(contractNumber5);
        contractDTO4.setContractName("Contract of Managerial Plan Development");
        contractDTO4.setContractTitle("Information for Executive Enhancement Plans");
        contractDTO4.setContractStartDate(dt);
        contractDTO4.setContractEndDate(dt.withDate(year, month, day));
        contractDTO4.setContractReviewDate(dt.withDate(year, month, day));
        contractDTO4.setContractSignedDate(dt.withDate(year, month, day));
        contractDTO4.setContractWEFDate(dt.withDate(year, month, day));
        contractDTOList.add(contractDTO4);

        return contractDTOList;
    }

}
