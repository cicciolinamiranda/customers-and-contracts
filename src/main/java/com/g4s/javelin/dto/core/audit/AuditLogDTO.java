package com.g4s.javelin.dto.core.audit;

import org.joda.time.DateTime;

import com.g4s.javelin.enums.ObjectTypeEnum;

public class AuditLogDTO {

    private String revisionNumber;
    private ObjectTypeEnum objectType;
    private DiffDTO body;
    private String ipAddress;
    private DateTime createdDate;

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(final String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public ObjectTypeEnum getObjectType() {
        return objectType;
    }

    public void setObjectType(final ObjectTypeEnum objectType) {
        this.objectType = objectType;
    }

    public DiffDTO getBody() {
        return body;
    }

    public void setBody(final DiffDTO body) {
        this.body = body;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final DateTime createdDate) {
        this.createdDate = createdDate;
    }

}
