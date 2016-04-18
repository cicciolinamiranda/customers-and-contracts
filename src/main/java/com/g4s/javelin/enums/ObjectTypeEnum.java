package com.g4s.javelin.enums;

public enum ObjectTypeEnum {

    CUSTOMERLOCATION("CUSTOMER_LOCATION"),
    POST("POST");

    private final String code;

    ObjectTypeEnum(final String code) {
        this.code = code;
    }

    public static ObjectTypeEnum findByCode(final String code) {
        for (final ObjectTypeEnum e : ObjectTypeEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
