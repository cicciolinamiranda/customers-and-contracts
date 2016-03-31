package com.g4s.javelin.enums;

public enum CostTypeEnum implements BaseEnum {

    ONEOFFCOST("ONE_OFF_COST", "One off Cost"),
    FIXEDRATE("FIXED_RATE", "Fixed Rate");

    private final String code;
    private final String description;

    CostTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static CostTypeEnum findByCode(final String code) {
        for (final CostTypeEnum e : CostTypeEnum.values()) {
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
