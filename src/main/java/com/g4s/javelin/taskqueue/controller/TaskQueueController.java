package com.g4s.javelin.taskqueue.controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;

@RestController
@RequestMapping("/tasks")
public class TaskQueueController {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueController.class.getName());

    @RequestMapping("/saveAuditLog")
    public void saveAuditLog(final AuditLogDTO auditLog) {
        //TODO: Call save to BigQuery
        LOGGER.info("Saving Audit Logs");
    }

}
