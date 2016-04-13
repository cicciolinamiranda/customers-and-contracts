package com.g4s.javelin.dto.core.location;

import org.joda.time.DateTime;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractDTO {

    private String contractNumber;
    private String contractName;
    private String contractTitle;
    private DateTime contractStartDate;
    private DateTime contractEndDate;
    private DateTime contractReviewDate;
    private DateTime contractSignedDate;
    private DateTime contractWEFDate;

    public DateTime getContractReviewDate() {
        return contractReviewDate;
    }

    public void setContractReviewDate(final DateTime contractReviewDate) {
        this.contractReviewDate = contractReviewDate;
    }

    public DateTime getContractWEFDate() {
        return contractWEFDate;
    }

    public void setContractWEFDate(final DateTime contractWEFDate) {
        this.contractWEFDate = contractWEFDate;
    }

    public DateTime getContractSignedDate() {
        return contractSignedDate;
    }

    public void setContractSignedDate(final DateTime contractSignedDate) {
        this.contractSignedDate = contractSignedDate;
    }

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(final String contractTitle) {
        this.contractTitle = contractTitle;
    }

    public DateTime getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(final DateTime contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public DateTime getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(final DateTime contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

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
