package com.g4s.javelin.dto.core.audit;

import java.util.List;

import org.joda.time.DateTime;

import com.g4s.javelin.enums.ObjectTypeEnum;

public class AuditLogDTO {

    private String revisionNumber;
    private ObjectTypeEnum objectType;
    private List<DiffDTO> body;
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

    public List<DiffDTO> getBody() {
        return body;
    }

    public void setBody(final List<DiffDTO> body) {
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

    @Override
    public String toString() {
        return "AuditLogDTO [revisionNumber=" + revisionNumber
                + ", objectType=" + objectType + ", body=" + body
                + ", ipAddress=" + ipAddress + ", createdDate=" + createdDate
                + "]";
    }
}
