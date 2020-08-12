package com.rabobank.assignment;

import com.rabobank.assignment.service.ValidateCustomerStatementService;

import java.io.IOException;
import java.net.URL;

public class CustomerStatementApplication {
    public static void main(String[] args) throws IOException {
        URL csvFileURL = Thread.currentThread().getContextClassLoader().getResource("records.csv");
        URL xmlFileURL = Thread.currentThread().getContextClassLoader().getResource("records.xml");
        String errorReportPath = "D:\\Rohit\\WorkSpace\\Rabobank\\CustomerStatement\\src\\main\\resources\\ErrorReport.csv";
        assert csvFileURL != null;
        assert xmlFileURL != null;
        ValidateCustomerStatementService.validateAndGenerateReport(csvFileURL.getFile(), xmlFileURL.getFile(), errorReportPath);

    }

}
