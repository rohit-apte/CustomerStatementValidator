package com.rabobank.assignment.enums;

public enum ErrorTypeEnum {

    DUPLICATE_RECORD("DUPLICATE_RECORD"), BALANCE_MISMATCH("BALANCE_MISMATCH");

    private String errorType;

    public String getErrorType() {
        return this.errorType;
    }

    ErrorTypeEnum(String errorType) {
        this.errorType = errorType;
    }

}
