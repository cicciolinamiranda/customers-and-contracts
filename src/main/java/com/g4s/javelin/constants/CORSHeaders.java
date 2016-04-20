package com.g4s.javelin.constants;

public enum CORSHeaders {
    ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),
    ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),
    ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),
    ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),
    ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),
    ORIGIN("Origin"),
    ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),
    ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),
    ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers");

    private String value;

    CORSHeaders(final String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
