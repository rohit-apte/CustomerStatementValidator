package com.rabobank.assignment.handler;

import com.rabobank.assignment.model.Record;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class XmlHandler extends DefaultHandler{
    private List<Record> records = null;
    private Record record = null;
    private StringBuilder data = null;

    public List<Record> getRecords() {
        return records;
    }
    boolean bAccountNo = false;
    boolean bDescription = false;
    boolean bStartBalance = false;
    boolean bMutation = false;
    boolean bEndBalance = false;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("record")) {
            String id = attributes.getValue("reference");
            record = new Record();
            record.setReference(Long.parseLong(id));
            if (records == null)
                records = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("accountNumber")) {
            bAccountNo = true;
        } else if (qName.equalsIgnoreCase("description")) {
            bDescription = true;
        } else if (qName.equalsIgnoreCase("startBalance")) {
            bStartBalance = true;
        } else if (qName.equalsIgnoreCase("mutation")) {
            bMutation = true;
        }
        else if (qName.equalsIgnoreCase("endBalance")) {
            bEndBalance = true;
        }
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (bAccountNo) {
            record.setAccountNo(data.toString());
            bAccountNo = false;
        } else if (bDescription) {
            record.setDescription(data.toString());
            bDescription = false;
        } else if (bStartBalance) {
            record.setStartBalance(new BigDecimal(data.toString()));
            bStartBalance = false;
        } else if (bMutation) {
            record.setMutation(new BigDecimal(data.toString()));
            bMutation = false;
        }
        else if (bEndBalance) {
            record.setEndBalance(new BigDecimal(data.toString()));
            bEndBalance = false;
        }

        if (qName.equalsIgnoreCase("record")) {
            records.add(record);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        data.append(new String(ch, start, length));
    }

}
