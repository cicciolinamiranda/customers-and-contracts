package com.g4s.javelin.dto.core.location;

import org.joda.time.DateTime;

/**
 * Created by apadilla on 4/8/16.
 */
public class ContractDTO {

    private String customerNumber;
    private String number;
    private String name;
    private String title;
    private DateTime startDate;
    private DateTime endDate;
    private DateTime reviewDate;
    private DateTime signedDate;
    private DateTime wefDate;

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
}
