package com.g4s.javelin.dto;

import org.joda.time.DateTime;

public class BaseDTO {

    private Long id;

    private String createdBy;

    private DateTime createdDate;

    private DateTime lastModifiedDate;

    private String ipAddress;

    private String reasonForChange;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(final DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getReasonForChange() {
        return reasonForChange;
    }

    public void setReasonForChange(final String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    @Override
    public String toString() {
        return "BaseDTO [id=" + id + ", createdBy=" + createdBy
                + ", createdDate=" + createdDate + ", lastModifiedDate="
                + lastModifiedDate + ", ipAddress=" + ipAddress
                + ", reasonForChange=" + reasonForChange + "]";
    }
}
