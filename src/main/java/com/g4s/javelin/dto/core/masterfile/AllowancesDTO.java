package com.g4s.javelin.dto.core.masterfile;

import com.g4s.javelin.dto.BaseMasterfileDTO;

//CSOFF: HiddenField
public class AllowancesDTO extends BaseMasterfileDTO {

    private boolean isBilled;
    private double amount;
    private Long associationId;
    private boolean isDeleted;

    public boolean isBilled() {
        return isBilled;
    }

    public void setBilled(final boolean isBilled) {
        this.isBilled = isBilled;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double amount) {
        this.amount = amount;
    }
    public Long getAssociationId() {
        return associationId;
    }
    public void setAssociationId(final Long associationId) {
        this.associationId = associationId;
    }
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(final boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
//CSON: HiddenField
