package com.g4s.javelin.enums;

/**
 * @author Jordan Duabe
 * @since 04/21/2016
 * <p/>
 * Application actions that need to be captured for audit logs.
 */
public enum LoggableActionsEnum {

    SAVE_CUSTOMER_LOCATION("SAVE_CUSTOMER_LOCATION"),
    ARCHIVE_CUSTOMER_LOCATION("ARCHIVE_CUSTOMER_LOCATION"),
    SAVE_POST("SAVE_POST");

    private final String code;

    LoggableActionsEnum(final String code) {
        this.code = code;
    }

    public static LoggableActionsEnum findByCode(final String code) {
        for (final LoggableActionsEnum e : LoggableActionsEnum.values()) {
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
