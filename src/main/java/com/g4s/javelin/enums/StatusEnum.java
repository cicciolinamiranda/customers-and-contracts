package com.g4s.javelin.enums;

public enum StatusEnum implements BaseEnum {

    INPROGRESS("IN_PROGRESS", "In Progress"),
    ARCHIVE("ARCHIVE", "Archive"),
    SUBMITTED("SUBMITTED", "Submit");
    
    private final String code;
    private final String description;

    private StatusEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static StatusEnum findByCode(final String code) {
        for (final StatusEnum e : StatusEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
