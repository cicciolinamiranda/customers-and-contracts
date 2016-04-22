package com.g4s.javelin.dto.core.audit;

//CSOFF_ALL:
public class AuditLogDTO {

    private String objectType;
    private String body;
    private String ip_address;
    private String reason;
    private String namespace = "US";
    private String object_id;
    private String action;
    private String revision_date;
    private String revised_by = "User";

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(final String ip_address) {
        this.ip_address = ip_address;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(final String object_id) {
        this.object_id = object_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(final String action) {
        this.action = action;
    }

    public String getRevision_date() {
        return revision_date;
    }

    public void setRevision_date(String revision_date) {
        this.revision_date = revision_date;
    }

    public String getRevised_by() {
        return revised_by;
    }

    public void setRevised_by(final String revised_by) {
        this.revised_by = revised_by;
    }

    @Override
    public String toString() {
        return "AuditLogDTO [objectType=" + objectType + ", body=" + body + ", ipAddress=" + ip_address + ", reason="
                + reason + "]";
    }
}
// CSON_ALL:
