package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//CSOFF: HiddenField
@Entity
@Table(name = "CUSTLOCATION_TRANSPORT")
public class CustomerLocationModeOfTransportModel {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_location_id", referencedColumnName = "id")
    private CustomerLocationModel customerLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private ModeTransportModel modeTransport;

    @Column(name = "IS_BILLED")
    private boolean isBilled;

    @Column(name = "COST_TYPE")
    private String costType;

    public CustomerLocationModel getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final CustomerLocationModel customerLocation) {
        this.customerLocation = customerLocation;
    }

    public boolean isBilled() {
        return isBilled;
    }

    public void setBilled(final boolean isBilled) {
        this.isBilled = isBilled;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(final String costType) {
        this.costType = costType;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public ModeTransportModel getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(final ModeTransportModel modeTransport) {
        this.modeTransport = modeTransport;
    }

}
//CSON: HiddenField

