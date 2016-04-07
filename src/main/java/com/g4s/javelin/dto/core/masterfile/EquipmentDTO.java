package com.g4s.javelin.dto.core.masterfile;

import com.g4s.javelin.dto.BaseMasterfileDTO;

//CSOFF: HiddenField
public class EquipmentDTO extends BaseMasterfileDTO {

    private boolean isBilled;
    private Long associationId;

    //FOR CUSTOMER LOCATION
    private String costType;
    private boolean isDeleted;

    //FOR POST
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

}
//CSON: HiddenField
