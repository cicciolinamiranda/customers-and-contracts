package com.g4s.javelin.data.model.post;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.g4s.javelin.data.model.masterfile.MasterfileModel;

@Embeddable
public class PreferencesModel {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_RELIGION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "religion_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> religions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_LANGUAGE",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "language_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_TRAINING",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "training_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> trainings;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_QUALIFICATION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "training_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> qualifications;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_PHYSICALCONDITION",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "physical_condition_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> physicalConditions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private MasterfileModel gender;

    @Column(name = "HEIGHT")
    private double height;

    public Set<MasterfileModel> getReligions() {
        return religions;
    }

    public void setReligions(final Set<MasterfileModel> religions) {
        this.religions = religions;
    }

    public Set<MasterfileModel> getLanguages() {
        return languages;
    }

    public void setLanguages(final Set<MasterfileModel> languages) {
        this.languages = languages;
    }

    public Set<MasterfileModel> getTrainings() {
        return trainings;
    }

    public void setTrainings(final Set<MasterfileModel> trainings) {
        this.trainings = trainings;
    }

    public Set<MasterfileModel> getQualifications() {
        return qualifications;
    }

    public void setQualifications(final Set<MasterfileModel> qualifications) {
        this.qualifications = qualifications;
    }

    public MasterfileModel getGender() {
        return gender;
    }

    public void setGender(final MasterfileModel gender) {
        this.gender = gender;
    }

    public Set<MasterfileModel> getPhysicalConditions() {
        return physicalConditions;
    }

    public void setPhysicalConditions(final Set<MasterfileModel> physicalConditions) {
        this.physicalConditions = physicalConditions;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

}
