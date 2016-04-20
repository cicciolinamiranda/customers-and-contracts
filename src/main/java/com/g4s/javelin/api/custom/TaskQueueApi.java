package com.g4s.javelin.api.custom;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;

/**
 * @author Sharlyn Mae Pandes
 * @since 4/18/2016
 *
 * 4/20/16 - Renamed from TaskQueueController
 * */

@RestController
@RequestMapping("/tasks")
public class TaskQueueApi {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueApi.class.getName());

    @RequestMapping("/saveAuditLog")
    public void saveAuditLog(final AuditLogDTO auditLog) {
        //TODO: Call save to BigQuery
        LOGGER.info("Saving Audit Logs");
    }

}
