package com.g4s.javelin.util;

import java.util.UUID;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.BaseDTO;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;

public class AuditLogUtil extends DiffDTO {

    public static AuditLogDTO getOldAndNewValue(final CustomerLocationDTO oldValue, final CustomerLocationDTO newValue) {
        return helper(oldValue, newValue);
    }

    public static AuditLogDTO getOldAndNewValue(final PostDTO oldValue, final PostDTO newValue) {
        return helper(oldValue, newValue);
    }

    private static AuditLogDTO helper(final BaseDTO oldValue, final BaseDTO newValue) {
        AuditLogDTO response = new AuditLogDTO();
        response.setBody(new DiffUtil().getOldAndNewValue(oldValue, newValue));
        response.setIpAddress(newValue.getIpAddress());
        response.setCreatedDate(DateTime.now());
        response.setRevisionNumber(UUID.randomUUID().toString());
        return response;
    }
}
