package com.g4s.javelin.dto.core.post;

import java.util.List;

import org.joda.time.DateTime;

import com.g4s.javelin.dto.BaseDTO;
import com.g4s.javelin.dto.core.masterfile.AllowancesDTO;
import com.g4s.javelin.dto.core.masterfile.EquipmentDTO;
import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;

//CSOFF: HiddenField
public class PostDTO extends BaseDTO {

    private String name;
    private String imageUrl;
    private boolean isBookOn;
    private boolean isBookOff;
    private boolean isCallIn;
    private String notes;
    private int numberOfEmployees;
    private DateTime startDate;
    private DateTime endDate;
    private String startDateStr;
    private String endDateStr;
    private boolean isIdentificationRequired;
    private String identificationNumber;

    private PreferencesDTO preferences;
    private MasterfileDTO role;
    private List<MasterfileDTO> skills;
    private List<MasterfileDTO> licenses;
    private List<MasterfileDTO> uniforms;
    private List<EquipmentDTO> equipments;
    private List<MasterfileDTO> schedulingConstraints;
    private List<MasterfileDTO> healthSafetyRequirements;
    private List<AllowancesDTO> allowances;

    private Long customerLocationId;

    private String startTime;
    private String endTime;
    private String postCover;

    public PreferencesDTO getPreferences() {
        return preferences;
    }
    public void setPreferences(final PreferencesDTO preferences) {
        this.preferences = preferences;
    }
    public MasterfileDTO getRole() {
        return role;
    }
    public void setRole(final MasterfileDTO role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }
    public void setNumberOfEmployees(final int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
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
    public boolean isIdentificationRequired() {
        return isIdentificationRequired;
    }
    public void setIdentificationRequired(final boolean isIdentificationRequired) {
        this.isIdentificationRequired = isIdentificationRequired;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    public void setIdentificationNumber(final String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public List<MasterfileDTO> getSkills() {
        return skills;
    }
    public void setSkills(final List<MasterfileDTO> skills) {
        this.skills = skills;
    }
    public List<MasterfileDTO> getLicenses() {
        return licenses;
    }
    public void setLicenses(final List<MasterfileDTO> licenses) {
        this.licenses = licenses;
    }
    public List<MasterfileDTO> getUniforms() {
        return uniforms;
    }
    public void setUniforms(final List<MasterfileDTO> uniforms) {
        this.uniforms = uniforms;
    }
    public List<EquipmentDTO> getEquipments() {
        return equipments;
    }
    public void setEquipments(final List<EquipmentDTO> equipments) {
        this.equipments = equipments;
    }
    public boolean isBookOn() {
        return isBookOn;
    }
    public void setBookOn(final boolean isBookOn) {
        this.isBookOn = isBookOn;
    }
    public boolean isBookOff() {
        return isBookOff;
    }
    public void setBookOff(final boolean isBookOff) {
        this.isBookOff = isBookOff;
    }
    public boolean isCallIn() {
        return isCallIn;
    }
    public void setCallIn(final boolean isCallIn) {
        this.isCallIn = isCallIn;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(final String notes) {
        this.notes = notes;
    }
    public List<MasterfileDTO> getSchedulingConstraints() {
        return schedulingConstraints;
    }
    public void setSchedulingConstraints(final List<MasterfileDTO> schedulingConstraints) {
        this.schedulingConstraints = schedulingConstraints;
    }
    public List<MasterfileDTO> getHealthSafetyRequirements() {
        return healthSafetyRequirements;
    }
    public void setHealthSafetyRequirements(
            final List<MasterfileDTO> healthSafetyRequirements) {
        this.healthSafetyRequirements = healthSafetyRequirements;
    }
    public Long getCustomerLocationId() {
        return customerLocationId;
    }
    public void setCustomerLocationId(final Long customerLocationId) {
        this.customerLocationId = customerLocationId;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }
    public String getPostCover() {
        return postCover;
    }
    public void setPostCover(final String postCover) {
        this.postCover = postCover;
    }
    public List<AllowancesDTO> getAllowances() {
        return allowances;
    }
    public void setAllowances(final List<AllowancesDTO> allowances) {
        this.allowances = allowances;
    }
}
//CSON: HiddenField

