package com.g4s.javelin.dto.core.audit;

import java.util.List;

import org.joda.time.DateTime;

import com.g4s.javelin.enums.LoggableActionsEnum;
import com.g4s.javelin.enums.ObjectTypeEnum;
//CSOFF_ALL:
public class AuditLogDTO {

    private LoggableActionsEnum loggableAction;
    private ObjectTypeEnum objectType;
    private List<DiffDTO> body;
    private String ip_address;
    private String reason;
    private String namespace;
    private String object_id;
    private String action;
    private DateTime revision_date;
    private String revised_by;

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
        return ip_address;
    }

    public void setIpAddress(final String ipAddress) {
        this.ip_address = ipAddress;
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

    public LoggableActionsEnum getLoggableAction() {
        return loggableAction;
    }

    public void setLoggableAction(final LoggableActionsEnum loggableAction) {
        this.loggableAction = loggableAction;
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

	public DateTime getRevisionDate() {
		return revision_date;
	}

	public void setRevisionDate(final DateTime revision_date) {
		this.revision_date = revision_date;
	}

	public String getRevisedBy() {
		return revised_by;
	}

	public void setRevisedBy(final String revised_by) {
		this.revised_by = revised_by;
	}

	@Override
    public String toString() {
        return "AuditLogDTO [objectType=" + objectType + ", body=" + body
                + ", ipAddress=" + ip_address + ", reason="
                + reason + "]";
    }
}
//CSON_ALL:
