package com.g4s.javelin.data.model.masterfile;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseMasterfileModel;

@Entity
@Table(name = "MF_TASK")
public class TaskModel extends BaseMasterfileModel {

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TaskActivityModel> taskActivities;

    public Set<TaskActivityModel> getTaskActivities() {
        return taskActivities;
    }

    public void setTaskActivities(final Set<TaskActivityModel> taskActivities) {
        this.taskActivities = taskActivities;
    }
}
