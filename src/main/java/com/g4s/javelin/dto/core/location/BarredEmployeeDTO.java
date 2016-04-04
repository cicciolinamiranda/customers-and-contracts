package com.g4s.javelin.dto.core.location;

import org.joda.time.DateTime;

//CSOFF: HiddenField
public class BarredEmployeeDTO {
    private Long id;
    private Long employeeId;
    private String title;
    private String firstName;
    private String lastName;
    private DateTime startDate;
    private DateTime endDate;
    private String startDateStr;
    private String endDateStr;
    private boolean isDeleted;

    public Long getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(final Long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(final String lastName) {
        this.lastName = lastName;
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
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
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
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
//CSON: HiddenField

