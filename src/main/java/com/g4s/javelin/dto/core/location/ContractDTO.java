package com.g4s.javelin.dto.core.location;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractDTO {

    private String contractNumber;
    private String contractName;

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(final String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(final String contractName) {
        this.contractName = contractName;
    }
}
