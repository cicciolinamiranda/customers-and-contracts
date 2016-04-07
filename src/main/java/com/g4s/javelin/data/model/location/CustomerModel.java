package com.g4s.javelin.data.model.location;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.g4s.javelin.data.model.BaseModel;

@Entity
@Table(name = "CUSTOMER")
public class CustomerModel extends BaseModel {

    @Column
    private String customerName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<CustomerLocationModel> customerLocation;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public List<CustomerLocationModel> getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(final List<CustomerLocationModel> customerLocation) {
        this.customerLocation = customerLocation;
    }

}
