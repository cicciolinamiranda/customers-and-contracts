package com.g4s.javelin.data.model.location;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

/**
 * Created by jalonzo on 4/14/16.
 */
@Entity
@Table(name = "CONTACT")
public class ContactModel extends BaseModel {

    @Column
    private String contactAccountNumber;

    @Column
    private String salutation;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private String jobTitle;

    @Column
    private String phone1;

    @Column
    private String phone2;

    @Column
    private String mobile;

    @Column
    private String email;

    @Column
    private String fax;

    @Column
    private String primaryContact;

    @Column
    private boolean inactive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    public String getContactAccountNumber() {
        return contactAccountNumber;
    }

    public void setContactAccountNumber(final String contactAccountNumber) {
        this.contactAccountNumber = contactAccountNumber;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(final String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(final String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(final String phone2) {
        this.phone2 = phone2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(final String fax) {
        this.fax = fax;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(final String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(final boolean inactive) {
        this.inactive = inactive;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerModel customer) {
        this.customer = customer;
    }
}
