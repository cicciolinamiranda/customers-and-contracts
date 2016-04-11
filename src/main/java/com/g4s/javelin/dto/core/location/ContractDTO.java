package com.g4s.javelin.dto.core.location;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractDTO {

    private Long contractNumber;
    private String contractName;

    public Long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(final Long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(final String contractName) {
        this.contractName = contractName;
    }
}
