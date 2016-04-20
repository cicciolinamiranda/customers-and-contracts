package com.g4s.javelin.taskqueue.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.g4s.javelin.dto.core.audit.AuditLogDTO;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public final class TaskQueueSubscriber {

    private static final Logger LOGGER = Logger.getLogger(TaskQueueSubscriber.class.getName());

    private TaskQueueSubscriber() {
    }

    public static boolean subscribeTask(final String taskQueue, final String url, final AuditLogDTO auditLog) {
        boolean isTaskCreated = false;
        final Queue queue = getTaskQueue(taskQueue);
        Map<String, String> params = transformObjectToParams(auditLog);

        if (url != null && !url.trim().isEmpty()) {
            final TaskOptions taskOptions = TaskOptions.Builder.withUrl(url);

            if (params != null && !params.isEmpty()) {
                for (String paramKey : params.keySet()) {
                    taskOptions.param(paramKey, params.get(paramKey));
                }
            }
            queue.add(taskOptions);
            isTaskCreated = true;
        }
        return isTaskCreated;
    }

    private static Map<String, String> transformObjectToParams(final AuditLogDTO auditLog) {
        Map<String, String> params = new HashMap<String, String>();
        LOGGER.info("Inside transformObjectToParams");
        //TODO: place transformation here
        return params;
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
