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

    @Column(name = "customer_number")
    private String customerNumber;

    @Column(name = "contract_number")
    private String number;

    @Column(name = "contract_name")
    private String name;

    @Column(name = "contract_title")
    private String title;

    @Column(name = "contract_start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column(name = "contract_end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column(name = "contract_review_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime reviewDate;

    @Column(name = "contract_signed_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime signedDate;

    @Column(name = "contract_wef_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime wefDate;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(final String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public DateTime getWefDate() {
        return wefDate;
    }

    public void setWefDate(final DateTime wefDate) {
        this.wefDate = wefDate;
    }

    public DateTime getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(final DateTime signedDate) {
        this.signedDate = signedDate;
    }

    public DateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(final DateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(final DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(final DateTime startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "ContractModel{" + "customerNumber='" + customerNumber
                + '\'' + ", name='" + name + '\''
                + ", number='" + number + '\''
                + ", title='" + title + '\''
                + ", startDate='" + startDate + '\''
                + ", endDate='" + endDate + '\''
                + ", signedDate='" + signedDate + '\''
                + ", reviewDate='" + reviewDate + '\''
                + ", wefDate='" + wefDate
                + '}';
    }
}
