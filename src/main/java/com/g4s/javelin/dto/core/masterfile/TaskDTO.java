package com.g4s.javelin.dto.core.masterfile;

import java.util.List;

import com.g4s.javelin.dto.BaseMasterfileDTO;

public class TaskDTO extends BaseMasterfileDTO {

    private List<TaskActivityDTO> taskActivities;

    public List<TaskActivityDTO> getTaskActivities() {
        return taskActivities;
    }
    public void setTaskActivities(final List<TaskActivityDTO> taskActivities) {
        this.taskActivities = taskActivities;
    }

}
