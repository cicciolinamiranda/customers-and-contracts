package com.g4s.javelin.dto.core.post;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.BaseDTO;
import com.g4s.javelin.dto.core.masterfile.RoleDTO;

//CSOFF: HiddenField
public class PostDTO extends BaseDTO {

    private PreferencesDTO preferences;
    private RoleDTO role;
    private String name;
    private String imageUrl;
    private int numberOfEmployees;
    private DateTime startDate;
    private DateTime endDate;
    private String startDateStr;
    private String endDateStr;
    private boolean isIdentificationRequired;
    private String identificationNumber;

    public PreferencesDTO getPreferences() {
        return preferences;
    }
    public void setPreferences(final PreferencesDTO preferences) {
        this.preferences = preferences;
    }
    public RoleDTO getRole() {
        return role;
    }
    public void setRole(final RoleDTO role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
    public void setNumberOfEmployees(final int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
    public DateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(final DateTime startDate) {
        this.startDate = startDate;
    }
    public DateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(final DateTime endDate) {
        this.endDate = endDate;
    }
    public String getStartDateStr() {
        return startDateStr;
    }
    public void setStartDateStr(final String startDateStr) {
        this.startDateStr = startDateStr;
    }
    public String getEndDateStr() {
        return endDateStr;
    }
    public void setEndDateStr(final String endDateStr) {
        this.endDateStr = endDateStr;
    }
    public boolean isIdentificationRequired() {
        return isIdentificationRequired;
    }
    public void setIdentificationRequired(final boolean isIdentificationRequired) {
        this.isIdentificationRequired = isIdentificationRequired;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public void setIdentificationNumber(final String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

}
//CSON: HiddenField

