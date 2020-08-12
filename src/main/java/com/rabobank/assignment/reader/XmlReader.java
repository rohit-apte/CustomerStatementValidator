package com.rabobank.assignment.reader;

import com.rabobank.assignment.commons.CommonUtil;
import com.rabobank.assignment.enums.ErrorTypeEnum;
import com.rabobank.assignment.handler.Validator;
import com.rabobank.assignment.handler.XmlHandler;
import com.rabobank.assignment.model.ErrorReport;
import com.rabobank.assignment.model.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {
    private static Logger LOG = LoggerFactory.getLogger(XmlReader.class);
    public List<ErrorReport> readAndValidate(String xmlFilePath) {
        LOG.info("Started reading XML file");
        List<ErrorReport> errorReportList = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            File xmlFile = new File(xmlFilePath);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlHandler handler = new XmlHandler();
            saxParser.parse(xmlFile, handler);
            List<Record> records = handler.getRecords();
            Validator.findDuplicates(records).forEach(record -> errorReportList.add(CommonUtil.setErrorReportValues(record, "xml", xmlFile.getAbsolutePath(), ErrorTypeEnum.DUPLICATE_RECORD)));
            records.forEach(record -> {
                if (!Validator.validateRecordBalance(record))
                    errorReportList.add(CommonUtil.setErrorReportValues(record, "xml", xmlFile.getAbsolutePath(), ErrorTypeEnum.BALANCE_MISMATCH));
            });
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("Error while parsing XML file");
        }
        return errorReportList;
    }
}
