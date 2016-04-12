package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "CUSTOMER")
public class CustomerModel extends BaseModel {

    @Column(
            unique = true,
            nullable = false)
    private String customerNumber;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;

    @Column
    private String addressLine4;

    @Column
    private String city;

    @Column
    private String zipCode;

    @Column
    private String state;

    @Column
    private String country;

    @Column
    private String countryCode;

    @Column
    private String DUNSnumber;

    @Column
    private String paymentMethod;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<CustomerLocationModel> customerLocation;


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getAddressLine4() {
        return addressLine4;
    }

    public void setAddressLine4(String addressLine4) {
        this.addressLine4 = addressLine4;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDUNSnumber() {
        return DUNSnumber;
    }

    public void setDUNSnumber(String DUNSnumber) {
        this.DUNSnumber = DUNSnumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<CustomerLocationModel> getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final List<CustomerLocationModel> customerLocation) {
        this.customerLocation = customerLocation;
    }

}
