package com.g4s.javelin.enums;

public enum MasterfileTypeEnum {
    LOCATION_EQUIPMENT("LOCATION_EQUIPMENT"),
    LOCATION_SKILLS("LOCATION_SKILLS"),
    GENDER("GENDER"),
    LANGUAGE("LANGUAGE"),
    LICENSE("LICENSE"),
    METHOD_OF_RECORDING("METHOD_OF_RECORDING"),
    MODE_TRANSPORT("MODE_TRANSPORT"),
    PHYSICAL_CONDITION("PHYSICAL_CONDITION"),
    POST_EQUIPMENT("POST_EQUIPMENT"),
    POST_SKILLS("POST_SKILLS"),
    PROOF_OF_DUTY("PROOF_OF_DUTY"),
    QUALIFICATION("QUALIFICATION"),
    RELIGION("RELIGION"),
    ROLE("ROLE"),
    SKILLS("SKILLS"),
    TRAINING("TRAINING"),
    UNIFORM("UNIFORM"),
    SCHEDULING_CONSTRAINTS("SCHEDULING_CONSTRAINTS"),
    CALL_IN_FREQUENCY("CALL_IN_FREQUENCY"),
    HEALTH_SAFETY_REQUIREMENTS("HEALTH_SAFETY_REQUIREMENTS");

    private final String code;

    MasterfileTypeEnum(final String code) {
        this.code = code;
    }

    public static MasterfileTypeEnum findByCode(final String code) {
        for (final MasterfileTypeEnum e : MasterfileTypeEnum.values()) {
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
