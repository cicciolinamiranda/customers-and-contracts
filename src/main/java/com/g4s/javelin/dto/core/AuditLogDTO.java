package com.g4s.javelin.dto.core;

import com.g4s.javelin.dto.BaseDTO;

public class AuditLogDTO extends BaseDTO {

    private String ipAddress;
    private String actions;

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public String getActions() {
        return actions;
    }
    public void setActions(final String actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "AuditLogDTO [ipAddress=" + ipAddress + ", actions=" + actions
                + ", getId()=" + getId() + ", getCreatedBy()=" + getCreatedBy()
                + ", getCreatedDate()=" + getCreatedDate()
                + ", getLastModifiedDate()=" + getLastModifiedDate() + "]";
    }
}
