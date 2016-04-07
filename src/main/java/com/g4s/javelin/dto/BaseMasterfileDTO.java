package com.g4s.javelin.dto;

public class BaseMasterfileDTO extends BaseDTO {

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
