package com.g4s.javelin.data.model.location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;
import com.g4s.javelin.data.model.masterfile.MasterfileModel;

//CSOFF: HiddenField
@Entity
@Table(name = "CUSTLOCATION_TRANSPORT")
public class CustomerLocationModeOfTransportModel extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_location_id", referencedColumnName = "id")
    private CustomerLocationModel customerLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transport_id", referencedColumnName = "id")
    private MasterfileModel modeTransport;

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

    public MasterfileModel getModeTransport() {
        return modeTransport;
    }

    public void setModeTransport(final MasterfileModel modeTransport) {
        this.modeTransport = modeTransport;
    }

}
//CSON: HiddenField

