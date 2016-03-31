package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.g4s.javelin.data.model.workorder.WorkOrderModel;
import com.g4s.javelin.enums.StatusEnum;

@Entity
@Table(name = "CUSTOMER_LOCATION")
public class CustomerLocationModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTLOCATION_WORKORDER",
        joinColumns = { @JoinColumn(name = "customer_location_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "work_order_id",
        referencedColumnName = "id", nullable = true) })
    private List<WorkOrderModel> workOrders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTLOCATION_SKILLS",
        joinColumns = { @JoinColumn(name = "customer_location_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "skills_id",
        referencedColumnName = "id", nullable = true) })
    private List<SkillsModel> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "CUSTLOCATION_TASK",
        joinColumns = { @JoinColumn(name = "customer_location_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "task_id",
        referencedColumnName = "id", nullable = true) })
    private List<TaskModel> tasks;

    @OneToMany(mappedBy = "customerLocation", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<SiteLocationModel> siteLocations;

    @OneToMany(mappedBy = "customerLocation", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<BarredEmployeeModel> barredEmployee;

    @OneToMany(mappedBy = "customerLocation", fetch = FetchType.EAGER)
    private List<CustomerLocationEquipmentModel> locationEquipments;

    @OneToMany(mappedBy = "customerLocation", fetch = FetchType.EAGER)
    private List<CustomerLocationModeOfTransportModel> locationTransports;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @Embedded
    private AddressModel address;

    @Column(name = "CREATED_DATE", nullable = true, updatable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Column(name = "SETUP_DATE", nullable = true, updatable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
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

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<WorkOrderModel> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(final List<WorkOrderModel> workOrders) {
        this.workOrders = workOrders;
    }

    public List<SkillsModel> getSkills() {
        return skills;
    }

    public void setSkills(final List<SkillsModel> skills) {
        this.skills = skills;
    }

    public List<SiteLocationModel> getSiteLocations() {
        return siteLocations;
    }

    public void setSiteLocations(final List<SiteLocationModel> siteLocations) {
        this.siteLocations = siteLocations;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(final List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(final AddressModel address) {
        this.address = address;
    }

    public DateTime getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(final DateTime setUpDate) {
        this.setUpDate = setUpDate;
    }

    public String getSopDetails() {
        return sopDetails;
    }

    public void setSopDetails(final String sopDetails) {
        this.sopDetails = sopDetails;
    }

    public String getLocationInstructionsApproval() {
        return locationInstructionsApproval;
    }

    public void setLocationInstructionsApproval(final String locationInstructionsApproval) {
        this.locationInstructionsApproval = locationInstructionsApproval;
    }

    public String getHealthSafetySurvey() {
        return healthSafetySurvey;
    }

    public void setHealthSafetySurvey(final String healthSafetySurvey) {
        this.healthSafetySurvey = healthSafetySurvey;
    }

    public String getTechnicalSurvey() {
        return technicalSurvey;
    }

    public void setTechnicalSurvey(final String technicalSurvey) {
        this.technicalSurvey = technicalSurvey;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(final CustomerModel customer) {
        this.customer = customer;
    }

    public String getLocationSurvey() {
        return locationSurvey;
    }

    public void setLocationSurvey(final String locationSurvey) {
        this.locationSurvey = locationSurvey;
    }

    public String getFloorPlan() {
        return floorPlan;
    }

    public void setFloorPlan(final String floorPlan) {
        this.floorPlan = floorPlan;
    }

    public List<BarredEmployeeModel> getBarredEmployee() {
        return barredEmployee;
    }

    public void setBarredEmployee(final List<BarredEmployeeModel> barredEmployee) {
        this.barredEmployee = barredEmployee;
    }

    @Override
    public String toString() {
        return "CustomerLocationModel [id=" + id + ", name=" + name
                + ", workOrders=" + workOrders + ", modeTransports="
                + ", skills=" + skills
                + ", tasks=" + tasks + ", siteLocations="
                + siteLocations + ", barredEmployee=" + barredEmployee
                + ", customer=" + customer + ", address=" + address
//                + ", createdDate=" + createdDate + ", setUpDate=" + setUpDate
                + ", sopDetails=" + sopDetails
                + ", locationInstructionsApproval="
                + locationInstructionsApproval + ", healthSafetySurvey="
                + healthSafetySurvey + ", technicalSurvey=" + technicalSurvey
                + ", locationSurvey=" + locationSurvey + ", floorPlan="
                + floorPlan + "]";
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(final StatusEnum status) {
        this.status = status;
    }

    public List<CustomerLocationEquipmentModel> getLocationEquipments() {
        return locationEquipments;
    }

    public void setLocationEquipments(
            final List<CustomerLocationEquipmentModel> locationEquipments) {
        this.locationEquipments = locationEquipments;
    }

    public List<CustomerLocationModeOfTransportModel> getLocationTransports() {
        return locationTransports;
    }

    public void setLocationTransports(
            final List<CustomerLocationModeOfTransportModel> locationTransports) {
        this.locationTransports = locationTransports;
    }

}
