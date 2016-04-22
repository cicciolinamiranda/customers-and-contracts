package com.g4s.javelin.util;

import com.g4s.javelin.dto.BaseDTO;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.dto.core.audit.DiffDTO;
import com.g4s.javelin.dto.core.location.CustomerLocationDTO;
import com.g4s.javelin.dto.core.post.PostDTO;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import java.io.IOException;

public class AuditLogUtil extends DiffDTO {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static AuditLogDTO getOldAndNewValue(final CustomerLocationDTO oldValue, final CustomerLocationDTO newValue) throws IOException {
        return helper(oldValue, newValue);
    }

    public static AuditLogDTO getOldAndNewValue(final PostDTO oldValue, final PostDTO newValue) throws IOException {
        return helper(oldValue, newValue);
    }

    private static AuditLogDTO helper(final BaseDTO oldValue, final BaseDTO newValue) throws IOException {
        AuditLogDTO response = new AuditLogDTO();

        if (null != oldValue) {
            response.setBody(OBJECT_MAPPER.writeValueAsString(new DiffUtil().getOldAndNewValue(oldValue, newValue)));
        } else {
            response.setBody(null);
        }

        response.setIpAddress(newValue.getIpAddress());
        response.setRevisionDate(DateTime.now());
       // response.setRevisionNumber(UUID.randomUUID().toString());
        return response;
    }
}
