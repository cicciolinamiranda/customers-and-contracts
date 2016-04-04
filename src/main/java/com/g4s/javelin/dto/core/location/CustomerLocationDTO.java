package com.g4s.javelin.dto.core.location;

import java.util.List;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.mock.IncidentLogMockDTO;

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
    private AddressDTO address;
    private DateTime startDate;
    private DateTime endDate;
    private String startDateStr;
    private String endDateStr;

    private String sopDetails;
    private String locationInstructionsApproval;
    private String healthSafetySurvey;
    private String technicalSurvey;
    private String locationSurvey;
    private String floorPlan;
    private CustomerDTO customer;
    private List<SiteLocationDTO> siteLocations;
    private List<IncidentLogMockDTO> incidents;
    private String statusStr;
    private ProofOfDutyDTO proofOfDuty;
    private MethodOfRecordingDTO methodOfRecording;

    //SiteLocationDTO

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public DateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(final DateTime createdDate) {
        this.createdDate = createdDate;
    }
    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }
    public void setEquipments(final List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }
    public List<ModeTransportDTO> getModeOfTransports() {
        return modeOfTransports;
    }
    public void setModeOfTransports(final List<ModeTransportDTO> modeOfTransports) {
        this.modeOfTransports = modeOfTransports;
    }
    public List<SkillsDTO> getSkills() {
        return skills;
    }
    public void setSkills(final List<SkillsDTO> skills) {
        this.skills = skills;
    }
    public List<TaskDTO> getTasks() {
        return tasks;
    }
    public void setTasks(final List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
    public List<BarredEmployeeDTO> getBarredEmployees() {
        return barredEmployees;
    }
    public void setBarredEmployees(final List<BarredEmployeeDTO> barredEmployees) {
        this.barredEmployees = barredEmployees;
    }

    public AddressDTO getAddress() {
        return address;
    }
    public void setAddress(final AddressDTO address) {
        this.address = address;
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
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public Long getWorkOrderId() {
        return workOrderId;
    }
    public void setWorkOrderId(final Long workOrderId) {
        this.workOrderId = workOrderId;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(final CustomerDTO customer) {
        this.customer = customer;
    }
    public String getStatusStr() {
        return statusStr;
    }
    public List<SiteLocationDTO> getSiteLocations() {
        return siteLocations;
    }

    public void setSiteLocations(final List<SiteLocationDTO> siteLocations) {
        this.siteLocations = siteLocations;
    }
    public void setStatusStr(final String statusStr) {
        this.statusStr = statusStr;
    }
    public DateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(final DateTime startDate) {
        this.startDate = startDate;
    }
    public DateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(final DateTime endDate) {
        this.endDate = endDate;
    }
    public String getStartDateStr() {
        return startDateStr;
    }
    public void setStartDateStr(final String startDateStr) {
        this.startDateStr = startDateStr;
    }
    public String getEndDateStr() {
        return endDateStr;
    }
    public void setEndDateStr(final String endDateStr) {
        this.endDateStr = endDateStr;
    }
    public List<IncidentLogMockDTO> getIncidents() {
        return incidents;
    }
    public void setIncidents(final List<IncidentLogMockDTO> incidents) {
        this.incidents = incidents;
    }
    public ProofOfDutyDTO getProofOfDuty() {
        return proofOfDuty;
    }
    public void setProofOfDuty(final ProofOfDutyDTO proofOfDuty) {
        this.proofOfDuty = proofOfDuty;
    }
    public MethodOfRecordingDTO getMethodOfRecording() {
        return methodOfRecording;
    }
    public void setMethodOfRecording(final MethodOfRecordingDTO methodOfRecording) {
        this.methodOfRecording = methodOfRecording;
    }

}
