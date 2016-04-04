package com.g4s.javelin.dto.core.location;

//CSOFF: HiddenField
public class EquipmentDTO {

    private Long id;
    private String equipmentName;
    private boolean isBilled;
    private String costType;
    private Long associationId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(final String equipmentName) {
        this.equipmentName = equipmentName;
    }

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

}
//CSON: HiddenField
