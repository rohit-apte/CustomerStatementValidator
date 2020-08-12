package com.rabobank.assignment.reader;

import com.rabobank.assignment.commons.CommonUtil;
import com.rabobank.assignment.enums.ErrorTypeEnum;
import com.rabobank.assignment.handler.Validator;
import com.rabobank.assignment.model.ErrorReport;
import com.rabobank.assignment.model.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvReader {
    private static Logger LOG = LoggerFactory.getLogger(CsvReader.class);
    public List<ErrorReport> readAndValidate(String csvFile) {
        LOG.info("Started reading CSV file");
        String line = "";
        Record record;
        List<ErrorReport> errorReportList = new ArrayList<>();
        ErrorReport errorReport;
        List<Record> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] recordArray = line.split(",");
                record = new Record(Long.parseLong(recordArray[0]), recordArray[1], recordArray[2], new BigDecimal(recordArray[3]), new BigDecimal(recordArray[4]), new BigDecimal(recordArray[5]));
                records.add(record);
                if (!Validator.validateRecordBalance(record)) {
                    errorReport = new ErrorReport();
                    errorReport.setReferenceNo(Long.parseLong(recordArray[0]));
                    errorReport.setAccountNo(recordArray[1]);
                    errorReport.setDescription(recordArray[2]);
                    errorReport.setErrorType(ErrorTypeEnum.BALANCE_MISMATCH);
                    errorReport.setFileType("csv");
                    errorReport.setFileName(csvFile);

                    errorReportList.add(errorReport);
                }
            }
            Validator.findDuplicates(records).forEach(rec -> errorReportList.add(CommonUtil.setErrorReportValues(rec, "csv", csvFile, ErrorTypeEnum.DUPLICATE_RECORD)));
        } catch (IOException e) {
            LOG.error("Error while opening CSV file");
        }
        return errorReportList;
    }
}
