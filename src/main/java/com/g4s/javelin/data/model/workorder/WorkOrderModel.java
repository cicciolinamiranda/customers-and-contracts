package com.g4s.javelin.data.model.workorder;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;
import com.g4s.javelin.data.model.location.CustomerLocationModel;

@Entity
@Table(name = "WORK_ORDER")
public class WorkOrderModel extends BaseModel {

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CUSTLOCATION_WORKORDER",
        joinColumns = {@JoinColumn(name = "work_order_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "customer_location_id", referencedColumnName = "id")})
    private List<CustomerLocationModel> customerLocations;

    public List<CustomerLocationModel> getCustomerLocations() {
        return customerLocations;
    }

    public void setCustomerLocations(final List<CustomerLocationModel> customerLocations) {
        this.customerLocations = customerLocations;
    }
}
