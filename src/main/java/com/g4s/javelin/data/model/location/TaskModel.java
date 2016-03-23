package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MF_TASK")
public class TaskModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TASK",
    joinColumns = { @JoinColumn(name = "task_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "customer_location_id"
    , referencedColumnName = "id") })
    private List<CustomerLocationModel> customerLocations;

    @OneToMany(mappedBy = "task", cascade = CascadeType.PERSIST)
    private List<TaskActivityModel> taskActivities;

    @Column
    private String taskName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CustomerLocationModel> getCustomerLocations() {
        return customerLocations;
    }

    public void setCustomerLocations(List<CustomerLocationModel> customerLocations) {
        this.customerLocations = customerLocations;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
