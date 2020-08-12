package com.rabobank.assignment.model;

import com.rabobank.assignment.enums.ErrorTypeEnum;

public class ErrorReport {
    private long referenceNo;
    private String accountNo;
    private String description;
    private ErrorTypeEnum errorType;
    private String fileType;
    private String fileName;


    public void setReferenceNo(long referenceNo) {
        this.referenceNo = referenceNo;
    }


    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setErrorType(ErrorTypeEnum errorType) {
        this.errorType = errorType;
    }


    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return this.referenceNo + "," + this.accountNo + "," + this.errorType + "," + this.fileType + "," + this.fileName + this.description;
    }
}
