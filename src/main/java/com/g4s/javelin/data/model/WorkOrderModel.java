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
@Table(name="WORK_ORDER")
public class WorkOrderModel {

	
    @Id
    @Column(
            unique = true,
            nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="CUSTLOCATION_WORKORDER",  
    joinColumns={@JoinColumn(name="work_order_id", referencedColumnName="id")},  
    inverseJoinColumns={@JoinColumn(name="customer_location_id", referencedColumnName="id")})  
    private List<CustomerLocationModel> customerLocations;  

}
