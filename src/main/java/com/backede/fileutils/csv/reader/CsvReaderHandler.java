/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.reader;

import com.backede.fileutils.exception.BeckedeFileException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class CsvReaderHandler {

    private Optional<Iterable<CSVRecord>> records = Optional.empty();
    private LinkedHashMap<String, Integer> headerMap = new LinkedHashMap<>();

    public CsvReaderHandler(String csvFilePath, Boolean addHeader) throws BeckedeFileException {
        readFile(csvFilePath, addHeader);
    }

    private void readFile(String csvFilePath, Boolean header) throws BeckedeFileException {
        try {
            CSVFormat CSV_FORMAT = Constants.CSV_FORMAT;

            if (header) {
                CSV_FORMAT = Constants.CSV_FORMAT.withFirstRecordAsHeader();
            }

            Charset forName = Charset.forName(StandardCharsets.UTF_8.name());
            CSVParser parse = CSVParser.parse(Paths.get(csvFilePath), forName, CSV_FORMAT);

            records = Optional.ofNullable(parse.getRecords());

            if (header) {
                for (Map.Entry<String, Integer> entry : parse.getHeaderMap().entrySet()) {
                    headerMap.put(entry.getKey(), entry.getValue());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(CsvReaderHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new BeckedeFileException("Error when reading: " + csvFilePath, ex);
        }
    }

    public Optional<Iterable<CSVRecord>> getRecords() {
        return records;
    }

    public Optional<LinkedHashMap<String, Integer>> getHeaderMap() {
        return Optional.ofNullable(headerMap);
    }

}
