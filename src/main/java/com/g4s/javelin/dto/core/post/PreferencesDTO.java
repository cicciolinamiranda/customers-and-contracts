package com.g4s.javelin.dto.core.post;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.MasterfileDTO;

public class PreferencesDTO {

    private List<MasterfileDTO> religions;
    private List<MasterfileDTO> trainings;
    private List<MasterfileDTO> qualifications;
    private List<MasterfileDTO> languages;
    private MasterfileDTO gender;
    private List<MasterfileDTO> physicalConditions;
    private double height;

    public List<MasterfileDTO> getReligions() {
        return religions;
    }
    public void setReligions(final List<MasterfileDTO> religions) {
        this.religions = religions;
    }
    public List<MasterfileDTO> getTrainings() {
        return trainings;
    }
    public void setTrainings(final List<MasterfileDTO> trainings) {
        this.trainings = trainings;
    }
    public List<MasterfileDTO> getQualifications() {
        return qualifications;
    }
    public void setQualifications(final List<MasterfileDTO> qualifications) {
        this.qualifications = qualifications;
    }
    public List<MasterfileDTO> getLanguages() {
        return languages;
    }
    public void setLanguages(final List<MasterfileDTO> languages) {
        this.languages = languages;
    }
    public MasterfileDTO getGender() {
        return gender;
    }
    public void setGender(final MasterfileDTO gender) {
        this.gender = gender;
    }
    public List<MasterfileDTO> getPhysicalConditions() {
        return physicalConditions;
    }
    public void setPhysicalConditions(final List<MasterfileDTO> physicalConditions) {
        this.physicalConditions = physicalConditions;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(final double height) {
        this.height = height;
    }
}
