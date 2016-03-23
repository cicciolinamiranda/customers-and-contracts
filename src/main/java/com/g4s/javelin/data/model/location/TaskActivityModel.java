package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MF_TASK_ACTIVITY")
public class TaskActivityModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskModel task;

    private String taskActivityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public String getTaskActivityName() {
        return taskActivityName;
    }

    public void setTaskActivityName(String taskActivityName) {
        this.taskActivityName = taskActivityName;
    }
}
