package com.g4s.javelin.data.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.g4s.javelin.enums.MasterfileTypeEnum;

@MappedSuperclass
public class BaseMasterfileModel extends BaseModel {

    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }

}
