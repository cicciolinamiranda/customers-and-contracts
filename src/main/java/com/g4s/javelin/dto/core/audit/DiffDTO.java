package com.g4s.javelin.dto.core.audit;

import java.util.ArrayList;
import java.util.List;

public class DiffDTO {

    private String fieldName;
    private List<String> changes;

    public DiffDTO() {
        changes = new ArrayList<String>();
    }

    public DiffDTO(final String fieldName, final List<String> changes) {
        this.fieldName = fieldName;
        this.changes = changes;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(final String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getChanges() {
        return changes;
    }

    public void setChanges(final List<String> changes) {
        this.changes = changes;
    }

}
