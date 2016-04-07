package com.g4s.javelin.dto.core.masterfile;

import com.g4s.javelin.dto.BaseMasterfileDTO;

//CSOFF: HiddenField
public class ModeTransportDTO extends BaseMasterfileDTO {

    private boolean isBilled;
    private String costType;
    private Long associationId;
    private boolean isDeleted;

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

}
//CSON: HiddenField

