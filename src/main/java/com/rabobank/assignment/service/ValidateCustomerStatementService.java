package com.rabobank.assignment.service;

import com.rabobank.assignment.model.ErrorReport;
import com.rabobank.assignment.reader.CsvReader;
import com.rabobank.assignment.reader.XmlReader;
import com.rabobank.assignment.writer.ErrorReportWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

public class ValidateCustomerStatementService {
    private static Logger LOG = LoggerFactory.getLogger(ValidateCustomerStatementService.class);

    public static void validateAndGenerateReport(String csvFilePath, String xmlFilePath, String errorReportPath) throws IOException {
        LOG.info("Started validation of customer statement");
        CsvReader csvReader = new CsvReader();
        XmlReader xmlReader = new XmlReader();
        ErrorReportWriter writer = new ErrorReportWriter();
        List<ErrorReport> errorReportList = csvReader.readAndValidate(csvFilePath);
        errorReportList.addAll(xmlReader.readAndValidate(xmlFilePath));

        if (!CollectionUtils.isEmpty(errorReportList))
            writer.writeReport(errorReportList, errorReportPath);
        LOG.info("Validation done");
    }
}
