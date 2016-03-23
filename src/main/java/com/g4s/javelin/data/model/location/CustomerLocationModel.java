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

import com.g4s.javelin.data.model.workorder.WorkOrderModel;

@Entity
@Table(name = "MF_CUSTOMER_LOCATION")
public class CustomerLocationModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_WORKORDER",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "work_order_id",
    referencedColumnName = "id") })
    private List<WorkOrderModel> workOrders;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TRANSPORT",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "mode_transport_id",
    referencedColumnName = "id") })
    private List<ModeTransportModel> modeTransports;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_SKILLS",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "skills_id",
    referencedColumnName = "id") })
    private List<SkillsModel> skills;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_EQUIPMENT",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "equipment_id",
    referencedColumnName = "id") })
    private List<EquipmentModel> equipments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TASK",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "task_id",
    referencedColumnName = "id") })
    private List<TaskModel> tasks;

    @OneToMany(mappedBy = "customerLocation", cascade = CascadeType.PERSIST)
    private List<SiteLocationModel> siteLocations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WorkOrderModel> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrderModel> workOrders) {
        this.workOrders = workOrders;
    }

    public List<ModeTransportModel> getModeTransports() {
        return modeTransports;
    }

    public void setModeTransports(List<ModeTransportModel> modeTransports) {
        this.modeTransports = modeTransports;
    }

    public List<SkillsModel> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsModel> skills) {
        this.skills = skills;
    }

    public List<SiteLocationModel> getSiteLocations() {
        return siteLocations;
    }

    public void setSiteLocations(List<SiteLocationModel> siteLocations) {
        this.siteLocations = siteLocations;
    }

    public List<EquipmentModel> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<EquipmentModel> equipments) {
        this.equipments = equipments;
    }
}
