package com.g4s.javelin.util;

import java.util.UUID;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;

public class AuditLogUtil extends DiffDTO {

    public AuditLogDTO getOldAndNewValue(final CustomerLocationDTO oldValue, final CustomerLocationDTO newValue) {
        AuditLogDTO response = new AuditLogDTO();
        response.setBody(new DiffUtil().getOldAndNewValue(oldValue, newValue));
        response.setIpAddress(newValue.getIpAddress());
        response.setCreatedDate(DateTime.now());
        response.setRevisionNumber(UUID.randomUUID().toString());
        return response;
    }

    public AuditLogDTO getOldAndNewValue(final PostDTO oldValue, final PostDTO newValue) {
        AuditLogDTO response = new AuditLogDTO();
        response.setBody(new DiffUtil().getOldAndNewValue(oldValue, newValue));
        response.setIpAddress(newValue.getIpAddress());
        response.setCreatedDate(DateTime.now());
        response.setRevisionNumber(UUID.randomUUID().toString());
        return response;
    }
}
