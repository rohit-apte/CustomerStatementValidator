package com.rabobank.assignment.handler;

import com.rabobank.assignment.model.Record;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    public static boolean validateRecordBalance(Record record) {
        BigDecimal calculatedBalance = record.getStartBalance().add(record.getMutation());
        return calculatedBalance.stripTrailingZeros().equals(record.getEndBalance());
    }
    public static List<Record> findDuplicates(List<Record> records) {
        Set<Long> referenceNums = new HashSet<>();
        return records.stream()
                .filter(record -> !referenceNums.add(record.getReference()))
                .collect(Collectors.toList());
    }

}
