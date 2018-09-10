/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.reader;

import com.backede.fileutils.csv.parser.CsvRecordWrapper;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class CsvRecordBuilder {

    /**
     *
     * @param normalizedRecords
     * @param headers
     * @return
     * @throws IOException
     */
    public static List<CsvRecordWrapper> build(Set<Map<String, String>> normalizedRecords, LinkedHashMap<String, Integer> headers) throws IOException {
        List<CsvRecordWrapper> builtRecords = new ArrayList<>();
        for (Map<String, String> record : normalizedRecords) {
            Set<String> headerKeys = headers.keySet();

            StringBuilder stringBuilder = new StringBuilder();
            headerKeys.forEach((key) -> {
                String data = record.get(key);
                stringBuilder.append(record.get(key)).append(Constants.DELIMITER.charValue());
            });

            StringBuilder fixedString = stringBuilder.deleteCharAt(
                    stringBuilder.lastIndexOf(Constants.DELIMITER.toString())
            );

            Reader reader = new StringReader(stringBuilder.toString());
            CSVParser parse = CSVParser.parse(reader, Constants.CSV_FORMAT
                    .withDelimiter(Constants.DELIMITER)
                    .withQuoteMode(QuoteMode.MINIMAL)
                    .withHeader(headerKeys.toArray(new String[0]))
            );

            for (CSVRecord csvRecord : parse.getRecords()) {
                builtRecords.add(new CsvRecordWrapper(csvRecord));
            }

        }
        return builtRecords;
    }

    public static Iterable<CsvRecordWrapper> build(Set<Map<String, String>> normalizedRecords) throws IOException {
        List<CsvRecordWrapper> builtRecords = new ArrayList<>();
        for (Map<String, String> record : normalizedRecords) {

            StringBuilder stringBuilder = new StringBuilder();

            StringBuilder fixedString = stringBuilder.deleteCharAt(
                    stringBuilder.lastIndexOf(Constants.DELIMITER.toString())
            );

            Reader reader = new StringReader(stringBuilder.toString());
            CSVParser parse = CSVParser.parse(reader, Constants.CSV_FORMAT
                    .withDelimiter(Constants.DELIMITER)
                    .withQuoteMode(QuoteMode.MINIMAL)
            );

            for (CSVRecord csvRecord : parse.getRecords()) {
                builtRecords.add(new CsvRecordWrapper(csvRecord));
            }

        }
        return builtRecords;
    }

    public static Set<Map<String, String>> normalizeRecords(Iterable<CSVRecord> records) {
        Set<Map<String, String>> normalizedData = new HashSet<>();
        for (CSVRecord record : records) {
            normalizedData.add(record.toMap());
        }
        return normalizedData;
    }

}
