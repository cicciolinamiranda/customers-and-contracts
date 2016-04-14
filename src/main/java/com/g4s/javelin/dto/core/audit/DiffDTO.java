package com.g4s.javelin.dto.core.audit;

public class DiffDTO {

    private String fieldName;
    private String oldValue;
    private String newValue;

    public DiffDTO() {
    }

    public DiffDTO(final String fieldName, final String oldValue,
            final String newValue) {
        super();
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

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

    @Override
    public String toString() {
        return "DiffDTO [fieldName=" + fieldName + ", oldValue=" + oldValue
                + ", newValue=" + newValue + "]";
    }
}
