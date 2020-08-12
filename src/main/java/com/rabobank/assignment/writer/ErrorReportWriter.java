package com.rabobank.assignment.writer;

import com.rabobank.assignment.model.ErrorReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ErrorReportWriter {
    private static Logger LOG = LoggerFactory.getLogger(ErrorReportWriter.class);

    public void writeReport(List<ErrorReport> errorReportList, String errorReportPath) throws IOException {
        LOG.info("Started generating report");
        FileWriter writer = new FileWriter(errorReportPath);
        writer.write("Reference,AccountNum,Error Type,File type,File Path,Description");
        writer.write("\n");
        errorReportList.forEach(obj -> {
            try {
                writer.write(obj.toString());
                writer.write("\n");
            } catch (IOException e) {
                LOG.error("Error occurred while writing Error Report");
            }
        });
        writer.flush();
        writer.close();
    }
}
