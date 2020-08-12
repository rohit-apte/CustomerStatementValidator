package com.rabobank.assignment.commons;

import com.rabobank.assignment.enums.ErrorTypeEnum;
import com.rabobank.assignment.model.ErrorReport;
import com.rabobank.assignment.model.Record;

public class CommonUtil {
    public static ErrorReport setErrorReportValues(Record record, String fileType, String filePath, ErrorTypeEnum errorType) {
        ErrorReport report = new ErrorReport();
        report.setReferenceNo(record.getReference());
        report.setAccountNo(record.getAccountNo());
        report.setDescription(record.getDescription());
        report.setErrorType(errorType);
        report.setFileType(fileType);
        report.setFileName(filePath);

        return report;
    }
}
