package com.g4s.javelin.dto.core.audit;

import java.util.List;

public class DiffDTO {

    private String fieldName;
    private List<String> oldValue;
    private List<String> newValue;

    public DiffDTO() {
    }

    public DiffDTO(final String fieldName, final List<String> oldValue,
            final List<String> newValue) {
        super();
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public List<String> getOldValue() {
        return oldValue;
    }

    public void setOldValue(final List<String> oldValue) {
        this.oldValue = oldValue;
    }

    public List<String> getNewValue() {
        return newValue;
    }

    public void setNewValue(final List<String> newValue) {
        this.newValue = newValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }


    @Override
    public String toString() {
        return "DiffDTO [fieldName=" + fieldName + ", oldValue=" + oldValue
                + ", newValue=" + newValue + "]";
    }
}
