package com.g4s.javelin.dto.core.location;

import org.joda.time.DateTime;

public class BarredEmployeeDTO {
    private Long id;
    private Long employeeId;
    private String title;
    private String firstName;
    private String lastName;
    private DateTime startDate;
    private DateTime endDate;
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
}
