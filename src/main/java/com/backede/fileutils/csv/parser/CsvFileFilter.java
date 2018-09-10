/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import com.backede.fileutils.csv.reader.CsvRecordBuilder;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class CsvFileFilter {

    Set<Map<String, String>> normalizedRecords;

    public CsvFileFilter(Iterable<CSVRecord> records) {
        normalizedRecords = CsvRecordBuilder.normalizeRecords(records);
    }

    public Set<String> getUniqueValuesFromColumn(String column) {
        Set<String> values = new LinkedHashSet<>();
        for (Map<String, String> normalizedRecord : normalizedRecords) {
            values.add(normalizedRecord.get(column));
        }
        return values;
    }

}
