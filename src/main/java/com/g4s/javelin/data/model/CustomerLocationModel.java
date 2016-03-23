package com.g4s.javelin.data.model;

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
@Table(name="CUSTOMER_LOCATION")
public class CustomerLocationModel {

    @Id
    @Column(
            unique = true,
            nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="CUSTLOCATION_WORKORDER",  
    joinColumns={@JoinColumn(name="customer_location_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="work_order_id", referencedColumnName="id")})  
    private List<WorkOrderModel> workOrders;  

}
