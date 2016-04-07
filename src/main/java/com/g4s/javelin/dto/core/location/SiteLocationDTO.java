package com.g4s.javelin.dto.core.location;

import com.g4s.javelin.dto.BaseDTO;

//CSOFF: HiddenField
public class SiteLocationDTO extends BaseDTO {

    private String siteLocationName;
    private String siteLocationEmail;
    private int contactNumber;
    private boolean isDeleted;

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
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
//CSON: HiddenField
