package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//CSOFF: HiddenField
@Entity
@Table(name = "MF_MODE_TRANSPORT")
public class ModeTransportModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String transportName;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(final String transportName) {
        this.transportName = transportName;
    }
}
//CSON: HiddenField
