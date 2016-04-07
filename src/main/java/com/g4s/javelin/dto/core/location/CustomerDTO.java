package com.g4s.javelin.dto.core.location;

import com.g4s.javelin.dto.BaseDTO;

public class CustomerDTO extends BaseDTO {

    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

}
