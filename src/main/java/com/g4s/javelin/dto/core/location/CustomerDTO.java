package com.g4s.javelin.dto.core.location;

import com.g4s.javelin.dto.BaseDTO;

public class CustomerDTO extends BaseDTO {

    private String customerName;

    private String customerNumber;

    private String customerCode;

    private String dunsNumber;

    private String accountNumber;

    private String accountDescription;

    private String manualCustomerCode;

    private String vatNumber;

    private String addressLine1;

    private String city;

    private String tbc;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(final String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(final String customerCode) {
        this.customerCode = customerCode;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(final String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(final String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getManualCustomerCode() {
        return manualCustomerCode;
    }

    public void setManualCustomerCode(final String manualCustomerCode) {
        this.manualCustomerCode = manualCustomerCode;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(final String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getTbc() {
        return tbc;
    }

    public void setTbc(final String tbc) {
        this.tbc = tbc;
    }
}
