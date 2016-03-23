package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MF_MODE_TRANSPORT")
public class ModeTransportModel {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_TRANSPORT",
    joinColumns = { @JoinColumn(name = "mode_transport_id",
    referencedColumnName = "id") },
    inverseJoinColumns = { @JoinColumn(name = "customer_location_id"
    , referencedColumnName = "id") })
    private List<CustomerLocationModel> customerLocations;

    @Column
    private String transportName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CustomerLocationModel> getCustomerLocations() {
        return customerLocations;
    }

    public void setCustomerLocations(List<CustomerLocationModel> customerLocations) {
        this.customerLocations = customerLocations;
    }

    public String getTransportName() {
        return transportName;
    }

    public void setTransportName(String transportName) {
        this.transportName = transportName;
    }
}
