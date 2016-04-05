package com.g4s.javelin.data.model.post;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.g4s.javelin.data.model.masterfile.GenderModel;
import com.g4s.javelin.data.model.masterfile.LanguageModel;
import com.g4s.javelin.data.model.masterfile.PhysicalConditionModel;
import com.g4s.javelin.data.model.masterfile.QualificationModel;
import com.g4s.javelin.data.model.masterfile.ReligionModel;
import com.g4s.javelin.data.model.masterfile.TrainingModel;

@Embeddable
public class PreferencesModel {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_RELIGION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "religion_id",
        referencedColumnName = "id", nullable = true) })
    private Set<ReligionModel> religions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_LANGUAGE",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "language_id",
        referencedColumnName = "id", nullable = true) })
    private Set<LanguageModel> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_TRAINING",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "training_id",
        referencedColumnName = "id", nullable = true) })
    private Set<TrainingModel> trainings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_QUALIFICATION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "training_id",
        referencedColumnName = "id", nullable = true) })
    private Set<QualificationModel> qualifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_PHYSICALCONDITION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "physical_condition_id",
        referencedColumnName = "id", nullable = true) })
    private Set<PhysicalConditionModel> physicalConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private GenderModel gender;

    @Column(name = "HEIGHT")
    private double height;

    public Set<ReligionModel> getReligions() {
        return religions;
    }

    public void setReligions(final Set<ReligionModel> religions) {
        this.religions = religions;
    }

    public Set<LanguageModel> getLanguages() {
        return languages;
    }

    public void setLanguages(final Set<LanguageModel> languages) {
        this.languages = languages;
    }

    public Set<TrainingModel> getTrainings() {
        return trainings;
    }

    public void setTrainings(final Set<TrainingModel> trainings) {
        this.trainings = trainings;
    }

    public Set<QualificationModel> getQualifications() {
        return qualifications;
    }

    public void setQualifications(final Set<QualificationModel> qualifications) {
        this.qualifications = qualifications;
    }

    public GenderModel getGender() {
        return gender;
    }

    public void setGender(final GenderModel gender) {
        this.gender = gender;
    }

    public Set<PhysicalConditionModel> getPhysicalConditions() {
        return physicalConditions;
    }

    public void setPhysicalConditions(final Set<PhysicalConditionModel> physicalConditions) {
        this.physicalConditions = physicalConditions;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

}
