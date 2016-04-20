package com.g4s.javelin.taskqueue.worker;

import java.util.logging.Logger;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.taskqueue.dto.TaskQueueResponseDTO;
import com.g4s.javelin.taskqueue.util.TaskQueueSubscriber;

public class AuditLogTaskWorker {

    private static final Logger LOGGER = Logger.getLogger(AuditLogTaskWorker.class.getName());

    public TaskQueueResponseDTO saveLog(final AuditLogDTO auditLogDto) {
        LOGGER.info("Inside AuditLogTaskWorker.saveLog");
        final boolean result = TaskQueueSubscriber.subscribeTask("audit-log-queue", "/tasks/saveAuditLog", auditLogDto);
        return new TaskQueueResponseDTO(result);
    }
}
