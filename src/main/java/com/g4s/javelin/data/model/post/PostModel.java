package com.g4s.javelin.data.model.post;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.g4s.javelin.data.model.BaseModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;

//CSOFF: HiddenField
@Entity
@Table(name = "POST")
public class PostModel extends BaseModel {

    @Embedded
    private PreferencesModel preferences;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private MasterfileModel role;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "NO_OF_EMPLOYEES")
    private int numberOfEmployees;

    @Column(name = "START_DATE", nullable = true, updatable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column(name = "END_DATE", nullable = true, updatable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @Column(name = "REQUIRE_IDENTIFICATION")
    private boolean isIdentificationRequired;

    @Column(name = "IDENTIFICATION_NUMBER")
    private String identificationNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_SKILL",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "post_skills_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_LICENSE",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "license_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> licenses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "POST_UNIFORM",
        joinColumns = { @JoinColumn(name = "post_id",
        referencedColumnName = "id", nullable = true) },
        inverseJoinColumns = { @JoinColumn(name = "uniform_id",
        referencedColumnName = "id", nullable = true) })
    private Set<MasterfileModel> uniforms;

    @Column(name = "IS_BOOK_ON")
    private boolean isBookOn;

    @Column(name = "IS_BOOK_OFF")
    private boolean isBookOff;

    @Column(name = "IS_CALL_IN")
    private boolean isCallIn;

    @Column(name = "NOTES")
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "call_in_frequency_id")
    private MasterfileModel callInFrequency;

    public PreferencesModel getPreferences() {
        return preferences;
    }

    public void setPreferences(final PreferencesModel preferences) {
        this.preferences = preferences;
    }

    public MasterfileModel getRole() {
        return role;
    }

    public void setRole(final MasterfileModel role) {
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

}
//CSON: HiddenField

