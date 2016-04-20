package com.g4s.javelin.api.location;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.g4s.javelin.api.AuditLogApi;
import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.g4s.javelin.taskqueue.dto.TaskQueueResponseDTO;
import com.g4s.javelin.taskqueue.worker.AuditLogTaskWorker;

@RunWith(MockitoJUnitRunner.class)
public class AuditLogApiTest {

    @Mock
    private AuditLogTaskWorker auditLogTaskWorker;

    @Mock
    private TaskQueueResponseDTO taskQueueDto;
    
    @Mock
    private AuditLogDTO auditLogDto;

    @InjectMocks
    private AuditLogApi auditLogApi = new AuditLogApi();

    @Test
    public void testSaveAuditLog() throws Exception {
        Mockito.when(auditLogTaskWorker.saveLog(auditLogDto)).thenReturn(taskQueueDto);
        assertNotNull(auditLogApi.saveAuditLog(auditLogDto));
    }
}
