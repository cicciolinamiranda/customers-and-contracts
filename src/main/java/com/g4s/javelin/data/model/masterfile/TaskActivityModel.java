package com.g4s.javelin.data.model.masterfile;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseMasterfileModel;

@Entity
@Table(name = "MF_TASK_ACTIVITY")
public class TaskActivityModel extends BaseMasterfileModel {

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskModel task;

    public TaskModel getTask() {
        return task;
    }

    public void setTask(final TaskModel task) {
        this.task = task;
    }

}
