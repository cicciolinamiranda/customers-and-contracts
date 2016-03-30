package com.g4s.javelin.enums;

public enum SearchCriteriaEnum implements BaseEnum {

    ADDRESS("ADDRESS", "Address"),
    CUSTOMER("CUSTOMER", "Customer Name"),
    ID("ID", "ID");

    private final String code;
    private final String description;

    SearchCriteriaEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static SearchCriteriaEnum findByCode(final String code) {
        for (final SearchCriteriaEnum criteria : SearchCriteriaEnum.values()) {
            if (criteria.getCode().equals(code)) {
                return criteria;
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
