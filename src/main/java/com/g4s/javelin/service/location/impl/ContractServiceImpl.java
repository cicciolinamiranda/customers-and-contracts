package com.g4s.javelin.service.location.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.format.DateTimeFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.data.model.location.ContractModel;
import com.g4s.javelin.data.repository.location.ContractRepository;
import com.g4s.javelin.dto.core.location.ContractDTO;
import com.g4s.javelin.service.location.ContractService;
import com.google.appengine.repackaged.com.google.api.client.util.Lists;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractServiceImpl implements ContractService {

    @Autowired
    @Lazy
    private ContractRepository contractRepository;

    private ModelMapper modelMapper;

    public ContractServiceImpl() {
        modelMapper = new ModelMapper();
    }

    @Override
    public ContractDTO saveContract(final ContractDTO contractDTO) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");

        ContractModel model;
        model = modelMapper.map(contractDTO, ContractModel.class);
        if (contractDTO.getId() != null) {
            model.setId(contractDTO.getId());
        }
        if (contractDTO.getStartDateStr() != null) {
            model.setStartDate(dtf.parseDateTime(contractDTO.getStartDateStr()));
        }

        if (contractDTO.getEndDateStr() != null) {
            model.setEndDate(dtf.parseDateTime(contractDTO.getEndDateStr()));
        }
        if (contractDTO.getSignedDateStr() != null) {
            model.setSignedDate(dtf.parseDateTime(contractDTO.getSignedDateStr()));
        }

        if (contractDTO.getReviewDateStr() != null) {
            model.setReviewDate(dtf.parseDateTime(contractDTO.getReviewDateStr()));
        }

        if (contractDTO.getWefDateStr() != null) {
            model.setWefDate(dtf.parseDateTime(contractDTO.getWefDateStr()));
        }
        contractRepository.save(model);
        return transformContract(model);
    }


    @Override
    public ContractDTO initializeContract() {
        ContractModel model = new ContractModel();
        contractRepository.save(model);
        return transformContract(model);
    }

    @Override
    public List<ContractDTO> getContractsDTO() {
        List<ContractModel> results = contractRepository.findAll();
        List<ContractDTO> list = Lists.newArrayList();
        for (ContractModel result : results) {
            list.add(transformContract(result));
        }
        return list;
    }

    @Override
    public List<ContractDTO> getContractByNumber(final String contractNumber) {
        List<ContractModel> results = contractRepository.findByNumber(contractNumber);
        List<ContractDTO> list = Lists.newArrayList();
        for (ContractModel result : results) {
            list.add(transformContract(result));
        }
        return list;
    }

    @Override
    public List<ContractDTO> getContractByName(final String contractName) {
        List<ContractModel> results = contractRepository.findByName(contractName);
        List<ContractDTO> list = Lists.newArrayList();
        for (ContractModel result : results) {
            list.add(transformContract(result));
        }
        return list;
    }

    @Override
    public List<ContractDTO> getContractByCustomerId(final Long customerId) {
        List<ContractModel> results = contractRepository.findByCustomerId(customerId);
        List<ContractDTO> list = Lists.newArrayList();
        for (ContractModel result : results) {
            list.add(transformContract(result));
        }
        return list;
    }

    @Override
    public List<ContractDTO> searchContract(final String searchTerm) {
        Long id = null;
        if (NumberUtils.isDigits(searchTerm)) {
            id = Long.valueOf(searchTerm);
        }
        String likeSearchTerm = "%" + searchTerm + "%";
        List<ContractModel> results = contractRepository.findBySearchTerm(id, likeSearchTerm);
        List<ContractDTO> list = Lists.newArrayList();
        for (ContractModel result : results) {
            list.add(transformContract(result));
        }
        return list;
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

    private ContractDTO transformContract(final ContractModel model) {
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        ContractDTO dto = modelMapper.map(model, ContractDTO.class);
        dto.setStartDateStr(dtf.print(model.getStartDate()));
        dto.setEndDateStr(dtf.print(model.getEndDate()));
        dto.setReviewDateStr(dtf.print(model.getReviewDate()));
        dto.setSignedDateStr(dtf.print(model.getSignedDate()));
        dto.setWefDateStr(dtf.print(model.getWefDate()));
        return dto;
    }

}
