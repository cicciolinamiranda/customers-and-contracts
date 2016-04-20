package com.g4s.javelin.taskqueue.dto;

public class TaskQueueResponseDTO {
    private boolean successful;

    public TaskQueueResponseDTO(final boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(final boolean successful) {
        this.successful = successful;
    }
}
