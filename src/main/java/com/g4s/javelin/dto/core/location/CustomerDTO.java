package com.g4s.javelin.dto.core.location;

public class CustomerDTO {

    private String customerName;
    private Long id;
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
