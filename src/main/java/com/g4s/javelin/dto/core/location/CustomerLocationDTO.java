package com.g4s.javelin.dto.core.location;

import java.util.List;

import org.joda.time.DateTime;

public class CustomerLocationDTO {

    private Long id;
    private Long workOrderId;
    private String name;
    private DateTime createdDate;
    private List<EquipmentDTO> equipments;
    private List<ModeTransportDTO> modeOfTransports;
    private List<SkillsDTO> skills;
    private List<TaskDTO> tasks;
    private List<BarredEmployeeDTO> barredEmployees;
    private List<IncidentLogMockDTO> incidentLogs;
    private AddressDTO address;
    private DateTime setUpDate;
    private String sopDetails;
    private String locationInstructionsApproval;
    private String healthSafetySurvey;
    private String technicalSurvey;
    private String locationSurvey;
    private String floorPlan;
    private CustomerDTO customer;
    //SiteLocationDTO
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public DateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }
    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }
    public void setEquipments(List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }
    public List<ModeTransportDTO> getModeOfTransports() {
        return modeOfTransports;
    }
    public void setModeOfTransports(List<ModeTransportDTO> modeOfTransports) {
        this.modeOfTransports = modeOfTransports;
    }
    public List<SkillsDTO> getSkills() {
        return skills;
    }
    public void setSkills(List<SkillsDTO> skills) {
        this.skills = skills;
    }
    public List<TaskDTO> getTasks() {
        return tasks;
    }
    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
    public List<BarredEmployeeDTO> getBarredEmployees() {
        return barredEmployees;
    }
    public void setBarredEmployees(List<BarredEmployeeDTO> barredEmployees) {
        this.barredEmployees = barredEmployees;
    }

    public List<IncidentLogMockDTO> getIncidentLogs() {
        return incidentLogs;
    }
    public void setIncidentLogs(List<IncidentLogMockDTO> incidentLogs) {
        this.incidentLogs = incidentLogs;
    }
    public AddressDTO getAddress() {
        return address;
    }
    public void setAddress(AddressDTO address) {
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getWorkOrderId() {
        return workOrderId;
    }
    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
