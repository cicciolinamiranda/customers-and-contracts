package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SITE_LOCATION")
public class SiteLocationModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_location_id", referencedColumnName = "id")
    private CustomerLocationModel customerLocation;

    @Column
    private String siteLocationName;

    @Column
    private String siteLocationEmail;

    @Column
    private int contactNumber;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

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

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(final int contactNumber) {
        this.contactNumber = contactNumber;
    }
}
