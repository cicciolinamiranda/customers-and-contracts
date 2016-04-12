package com.g4s.javelin.dto.core.audit;

public class DiffDTO {

    private String fieldName;
    private String oldValue;
    private String newValue;
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }
    public String getOldValue() {
        return oldValue;
    }
    public void setOldValue(final String oldValue) {
        this.oldValue = oldValue;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(final String newValue) {
        this.newValue = newValue;
    }
}
