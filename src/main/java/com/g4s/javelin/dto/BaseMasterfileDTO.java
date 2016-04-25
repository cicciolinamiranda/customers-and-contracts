package com.g4s.javelin.dto;

import org.javers.core.metamodel.annotation.Entity;
import org.javers.core.metamodel.annotation.Id;

@Entity
public class BaseMasterfileDTO extends BaseDTO {

    @Id
    private String name;
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
