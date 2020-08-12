package com.rabobank.assignment.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(JUnit4.class)
public class ValidateCustomerStatmentTest {
    private URL errorFileURL = Thread.currentThread().getContextClassLoader().getResource("ErrorReportTest.csv");

    @Before
    public void prepare() {
        if (errorFileURL != null) {
            new File(errorFileURL.getFile()).delete();
        }
    }

    @Test
    public void validateAndGenerateReport() throws Exception {
        String errorReportPath = "src/test/resources/ErrorReportTest.csv";
        String csvFile = "src/test/resources/records.csv";
        String xmlFile = "src/test/resources/records.xml";
        ValidateCustomerStatementService.validateAndGenerateReport(csvFile, xmlFile, errorReportPath);

        assertNotNull(errorFileURL);

        try (BufferedReader br = new BufferedReader(new FileReader(errorReportPath))) {
            br.readLine();
            assertEquals(5, br.lines().count());
        }
    }

}
