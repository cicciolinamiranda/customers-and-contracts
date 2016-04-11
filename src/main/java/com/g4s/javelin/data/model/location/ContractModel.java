package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

/**
 * Created by apadilla on 4/8/16.
 */
@Entity
@Table(name = "CONTRACT")
public class ContractModel extends BaseModel {

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "contract_name")
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
