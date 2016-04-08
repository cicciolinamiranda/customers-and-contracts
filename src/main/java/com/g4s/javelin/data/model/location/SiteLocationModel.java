package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table(name = "SITE_LOCATION")
public class SiteLocationModel extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "customer_location_id", referencedColumnName = "id")
    private CustomerLocationModel customerLocation;

    @Column
    private String siteLocationName;

    @Column
    private String siteLocationEmail;

    @Column
    private String contactNumber;

    public CustomerLocationModel getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final CustomerLocationModel customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getSiteLocationName() {
        return siteLocationName;
    }

    public void setSiteLocationName(final String siteLocationName) {
        this.siteLocationName = siteLocationName;
    }

    public String getSiteLocationEmail() {
        return siteLocationEmail;
    }

    public void setSiteLocationEmail(final String siteLocationEmail) {
        this.siteLocationEmail = siteLocationEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
