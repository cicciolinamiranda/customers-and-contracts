package com.g4s.javelin.taskqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.g4s.javelin.taskqueue.worker.AuditLogTaskWorker;

@Configuration
@Lazy
public class TaskQueueContextConfig {
    @Bean(name = "auditLogTaskWorker")
    public AuditLogTaskWorker getAuditLogTaskWorker() {
        return new AuditLogTaskWorker();
    }
}
