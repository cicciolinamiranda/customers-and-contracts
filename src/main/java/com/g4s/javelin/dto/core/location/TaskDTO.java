package com.g4s.javelin.dto.core.location;

import java.util.List;

public class TaskDTO {

    private Long id;
    private String taskName;
    private List<TaskActivityDTO> taskActivities;
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(final String taskName) {
        this.taskName = taskName;
    }
    public List<TaskActivityDTO> getTaskActivities() {
        return taskActivities;
    }
    public void setTaskActivities(final List<TaskActivityDTO> taskActivities) {
        this.taskActivities = taskActivities;
    }
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }

}
