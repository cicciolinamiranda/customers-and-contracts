package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.g4s.javelin.data.model.AddressModel;
import com.g4s.javelin.data.model.workorder.WorkOrderModel;

@Entity
@Table(name = "CUSTOMER_LOCATION")
public class CustomerLocationModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_WORKORDER",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "work_order_id",
    referencedColumnName = "id") })
    private List<WorkOrderModel> workOrders;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TRANSPORT",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "mode_transport_id",
    referencedColumnName = "id") })
    private List<ModeTransportModel> modeTransports;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_SKILLS",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "skills_id",
    referencedColumnName = "id") })
    private List<SkillsModel> skills;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_EQUIPMENT",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "equipment_id",
    referencedColumnName = "id") })
    private List<EquipmentModel> equipments;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TASK",
    joinColumns = { @JoinColumn(name = "customer_location_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "task_id",
    referencedColumnName = "id") })
    private List<TaskModel> tasks;

    @OneToMany(mappedBy = "customerLocation", cascade = CascadeType.PERSIST)
    private List<SiteLocationModel> siteLocations;

    @OneToMany(mappedBy = "customerLocation", cascade = CascadeType.PERSIST)
    private List<BarredEmployeeModel> barredEmployee;

    @Embedded
    private AddressModel address;
    private DateTime createdDate;

    @Column(name = "SETUP_DATE")
    private DateTime setUpDate;
    @Column(name = "SOP_DETAILS")
    private String sopDetails;
    @Column(name = "LOCATION_INSTRUCTIONS_APPROVAL")
    private String locationInstructionsApproval;
    @Column(name = "HEALTH_SAFETY_SURVEY")
    private String healthSafetySurvey;
    @Column(name = "TECHNICAL_SURVEY")
    private String technicalSurvey;
    @Column(name = "LOCATION_SURVEY")
    private String locationSurvey;
    @Column(name = "FLOOR_PLAN")
    private String floorPlan;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public DateTime getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(DateTime setUpDate) {
        this.setUpDate = setUpDate;
    }

    public String getSopDetails() {
        return sopDetails;
    }

    public void setSopDetails(String sopDetails) {
        this.sopDetails = sopDetails;
    }

    public String getLocationInstructionsApproval() {
        return locationInstructionsApproval;
    }

    public void setLocationInstructionsApproval(String locationInstructionsApproval) {
        this.locationInstructionsApproval = locationInstructionsApproval;
    }

    public String getHealthSafetySurvey() {
        return healthSafetySurvey;
    }

    public void setHealthSafetySurvey(String healthSafetySurvey) {
        this.healthSafetySurvey = healthSafetySurvey;
    }

    public String getTechnicalSurvey() {
        return technicalSurvey;
    }

    public void setTechnicalSurvey(String technicalSurvey) {
        this.technicalSurvey = technicalSurvey;
    }

    public String getLocationSurvey() {
        return locationSurvey;
    }

    public void setLocationSurvey(String locationSurvey) {
        this.locationSurvey = locationSurvey;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(String floorPlan) {
        this.floorPlan = floorPlan;
    }
}
