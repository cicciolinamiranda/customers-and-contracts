package com.g4s.javelin.enums;

public enum AllowableExtensionEnum {

    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png");

    private String value;

    AllowableExtensionEnum(final String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
