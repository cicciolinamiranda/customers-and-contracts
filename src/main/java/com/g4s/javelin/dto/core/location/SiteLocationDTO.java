package com.g4s.javelin.dto.core.location;


public class SiteLocationDTO {

    private Long id;
    private String siteLocationName;
    private String siteLocationEmail;
    private int contactNumber;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSiteLocationName() {
        return siteLocationName;
    }
    public void setSiteLocationName(String siteLocationName) {
        this.siteLocationName = siteLocationName;
    }
    public String getSiteLocationEmail() {
        return siteLocationEmail;
    }
    public void setSiteLocationEmail(String siteLocationEmail) {
        this.siteLocationEmail = siteLocationEmail;
    }
    public int getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }
}
