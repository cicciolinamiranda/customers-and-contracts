package com.g4s.javelin.dto.core.location;

import com.g4s.javelin.dto.core.masterfile.MasterFileDTO;

public class CreateCustomerLocationDTO {

    Long customerLocationId;
    MasterFileDTO masterfile;
    public Long getCustomerLocationId() {
        return customerLocationId;
    }
    public void setCustomerLocationId(final Long customerLocationId) {
        this.customerLocationId = customerLocationId;
    }
    public MasterFileDTO getMasterfile() {
        return masterfile;
    }
    public void setMasterfile(final MasterFileDTO masterfile) {
        this.masterfile = masterfile;
    }
}
