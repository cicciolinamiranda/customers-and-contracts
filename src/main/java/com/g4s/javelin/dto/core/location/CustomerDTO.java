package com.g4s.javelin.dto.core.location;

import java.util.List;

import com.g4s.javelin.dto.BaseDTO;

public class CustomerDTO extends BaseDTO {

    private String customerNumber;

    private String customerName;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String addressLine4;

    private String city;

    private String zipCode;

    private String state;

    private String country;

    private String countryCode;

    private String dunsNumber;

    private String paymentMethod;

    private List<CustomerLocationDTO> customerLocation;


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(final String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(final String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(final String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(final String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<CustomerLocationDTO> getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final List<CustomerLocationDTO> customerLocation) {
        this.customerLocation = customerLocation;
    }

    @Override
    public String toString() {
        return "CustomerModel{" + "customerNumber='" + customerNumber
                + '\'' + ", customerName='" + customerName + '\''
                + ", addressLine1='" + addressLine1 + '\''
                + ", addressLine2='" + addressLine2 + '\''
                + ", addressLine3='" + addressLine3 + '\''
                + ", addressLine4='" + addressLine4 + '\''
                + ", city='" + city + '\''
                + ", zipCode='" + zipCode + '\''
                + ", state='" + state + '\''
                + ", country='" + country + '\''
                + ", countryCode='" + countryCode + '\''
                + ", dunsNumber='" + dunsNumber + '\''
                + ", paymentMethod='" + paymentMethod + '\''
                + ", customerLocation=" + customerLocation
                + '}';
    }
}
