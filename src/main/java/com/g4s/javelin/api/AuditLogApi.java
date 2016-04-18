package com.g4s.javelin.api;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.constants.ApiConstants;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.taskqueue.dto.TaskQueueResponseDTO;
import com.g4s.javelin.taskqueue.worker.AuditLogTaskWorker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * @author Sharlyn Mae Pandes
 * @since 04/13/2016
 */

@Api(
        name = ApiConstants.API_NAME,
        version = ApiConstants.API_VERSION,
        namespace = @ApiNamespace(ownerDomain = ApiConstants.API_NAMESPACE_OWNER_DOMAIN,
                ownerName = ApiConstants.API_NAMESPACE_OWNER_NAME),
        description = ApiConstants.API_DESCRIPTION)
public class AuditLogApi {

    private static final Logger LOGGER = Logger.getLogger(AuditLogApi.class.getName());

    @Autowired
    @Lazy
    @Qualifier("auditLogTaskWorker")
    private AuditLogTaskWorker auditLogTaskWorker;

    @ApiMethod(
            name = "workorder.audit.log.save",
            path = "workorder/audit-log/save",
            httpMethod = ApiMethod.HttpMethod.GET)
    public TaskQueueResponseDTO saveAuditLog(final AuditLogDTO auditLog) {
        LOGGER.info(auditLog.toString());
        return auditLogTaskWorker.saveLog(auditLog);
    }
}
