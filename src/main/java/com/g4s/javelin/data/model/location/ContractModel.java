package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import org.joda.time.DateTime;

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

    @Column(name = "contract_title")
    private String contractTitle;

    @Column(name = "contract_start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime contractStartDate;

    @Column(name = "contract_end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime contractEndDate;

    @Column(name = "contract_review_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime contractReviewDate;

    @Column(name = "contract_signed_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime contractSignedDate;

    @Column(name = "contract_wef_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
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

    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(final String contractTitle) {
        this.contractTitle = contractTitle;
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
