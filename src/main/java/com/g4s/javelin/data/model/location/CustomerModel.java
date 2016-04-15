package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table(name = "CUSTOMER")
public class CustomerModel extends BaseModel {

    @Column(unique = true,
            length = 360)
    private String customerName;

    @Column(length = 30)
    private String customerNumber;

    @Column(length = 30)
    private String customerCode;

    @Column(length = 30)
    private String dunsNumber;

    @Column(unique = true,
            length = 30)
    private String accountNumber;

    @Column(length = 240)
    private String accountDescription;

    @Column
    private String manualCustomerCode;

    @Column
    private String vatNumber;

    @Column
    private String addressLine1;

    @Column
    private String city;

    @Column
    private String tbc;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<CustomerLocationModel> customerLocation;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ContactModel> contacts;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public List<CustomerLocationModel> getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final List<CustomerLocationModel> customerLocation) {
        this.customerLocation = customerLocation;
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
