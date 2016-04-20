package com.g4s.javelin.dto.core.location;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.BaseDTO;


/**
 * Created by apadilla on 4/8/16.
 */
public class ContractDTO extends BaseDTO {

    private String customerNumber;
    private String number;
    private String name;
    private String title;
    private DateTime startDate;
    private DateTime endDate;
    private DateTime reviewDate;
    private DateTime signedDate;
    private DateTime wefDate;
    private CustomerDTO customer;
    private String startDateStr;
    private String endDateStr;
    private String reviewDateStr;
    private String signedDateStr;
    private String wefDateStr;

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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerDTO customer) {
        this.customer = customer;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(final String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(final String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getReviewDateStr() {
        return reviewDateStr;
    }

    public void setReviewDateStr(final String reviewDateStr) {
        this.reviewDateStr = reviewDateStr;
    }

    public String getSignedDateStr() {
        return signedDateStr;
    }

    public void setSignedDateStr(final String signedDateStr) {
        this.signedDateStr = signedDateStr;
    }

    public String getWefDateStr() {
        return wefDateStr;
    }

    public void setWefDateStr(final String wefDateStr) {
        this.wefDateStr = wefDateStr;
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
