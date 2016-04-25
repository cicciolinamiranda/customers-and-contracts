package com.g4s.javelin.constants;

public final class ExceptionMessageConstants {
    public static final String NO_CONTENTS_FOUND = "No records found";
    public static final String PERSIST_FAILED = "Cannot persist data";
    public static final String NULL_EMPLOYMENT_DETAILS_FOUND = "No employment details found";
    public static final String NULL_DATE = "Date String is required";
    public static final String NULL_COUNTRYCODE = "Country code is required";
    public static final String INVALID_LOGIN = "Invalid login credentials";
    public static final String NULL_WORKORDER_FOUND = "No workorder found on the provided id";
    public static final String NULL_EXISTING_CUSTLOC_FOUND = "No customer location found on the provided id";
    public static final String POST_DUPLICATE_NAME = "Post name is already used.";
    public static final String POST_REQUIRED_NAME = "Post name is required";
    public static final String INVALID_IMAGE_FILE = "Image format invalid. Allowable formats are JPG, JPEG and PNG";
    public static final String FILE_EMPTY = "Failed to upload. File is empty";

    private ExceptionMessageConstants() {
    }
}
