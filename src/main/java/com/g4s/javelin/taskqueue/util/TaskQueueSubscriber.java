package com.g4s.javelin.taskqueue.util;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

public final class TaskQueueSubscriber {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueSubscriber.class.getName());

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private TaskQueueSubscriber() {
    }

    public static boolean subscribeTask(final String taskQueue, final String url, final AuditLogDTO auditLog) {
        boolean isSubscribed = false;

        final Queue queue = getTaskQueue(taskQueue);

        try {
            queue.add(TaskOptions.Builder.withUrl(url).payload(OBJECT_MAPPER.writeValueAsString(auditLog)));

            isSubscribed = true;
        } catch (final IOException e) {
            LOGGER.severe(e.getMessage());
        }

        return isSubscribed;
    }

    private static Queue getTaskQueue(final String taskQueue) {
        Queue queue;

        if (taskQueue != null && !taskQueue.isEmpty()) {
            queue = QueueFactory.getQueue(taskQueue);
        } else {
            queue = QueueFactory.getDefaultQueue();
        }
        return queue;
    }
}
