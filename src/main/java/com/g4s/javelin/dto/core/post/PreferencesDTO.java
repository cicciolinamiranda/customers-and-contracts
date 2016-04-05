package com.g4s.javelin.dto.core.post;

import java.util.List;

import com.g4s.javelin.dto.core.masterfile.GenderDTO;
import com.g4s.javelin.dto.core.masterfile.LanguageDTO;
import com.g4s.javelin.dto.core.masterfile.PhysicalConditionDTO;
import com.g4s.javelin.dto.core.masterfile.QualificationDTO;
import com.g4s.javelin.dto.core.masterfile.ReligionDTO;
import com.g4s.javelin.dto.core.masterfile.TrainingDTO;

public class PreferencesDTO {

    private List<ReligionDTO> religions;
    private List<TrainingDTO> trainings;
    private List<QualificationDTO> qualifications;
    private List<LanguageDTO> languages;
    private GenderDTO gender;
    private List<PhysicalConditionDTO> physicalConditions;
    private double height;

    public List<ReligionDTO> getReligions() {
        return religions;
    }
    public void setReligions(final List<ReligionDTO> religions) {
        this.religions = religions;
    }
    public List<TrainingDTO> getTrainings() {
        return trainings;
    }
    public void setTrainings(final List<TrainingDTO> trainings) {
        this.trainings = trainings;
    }
    public List<QualificationDTO> getQualifications() {
        return qualifications;
    }
    public void setQualifications(final List<QualificationDTO> qualifications) {
        this.qualifications = qualifications;
    }
    public List<LanguageDTO> getLanguages() {
        return languages;
    }
    public void setLanguages(final List<LanguageDTO> languages) {
        this.languages = languages;
    }
    public GenderDTO getGender() {
        return gender;
    }
    public void setGender(final GenderDTO gender) {
        this.gender = gender;
    }
    public List<PhysicalConditionDTO> getPhysicalConditions() {
        return physicalConditions;
    }
    public void setPhysicalConditions(final List<PhysicalConditionDTO> physicalConditions) {
        this.physicalConditions = physicalConditions;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(final double height) {
        this.height = height;
    }
}
