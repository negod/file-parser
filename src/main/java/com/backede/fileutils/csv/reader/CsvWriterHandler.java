/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.reader;

import com.backede.fileutils.csv.parser.CsvRecordWrapper;
import com.backede.fileutils.exception.BeckedeFileException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class CsvWriterHandler {

    private final String path;

    public CsvWriterHandler(String path) {
        this.path = path;
    }

    private Iterable<CSVRecord> convertToCsvRecord(Iterable<CsvRecordWrapper> wrapper) {
        List<CSVRecord> records = new ArrayList<>();
        for (CsvRecordWrapper csvRecordWrapper : wrapper) {
            records.add(csvRecordWrapper.getRecord());
        }
        return records;
    }

    public Boolean writeScvWrapper(LinkedHashMap<String, Integer> headers, Iterable<CsvRecordWrapper> data) throws BeckedeFileException {
        Iterable<CSVRecord> convertToCsvRecord = convertToCsvRecord(data);
        return writeScv(headers, convertToCsvRecord);
    }

    public Boolean writeScv(LinkedHashMap<String, Integer> headers, Iterable<CSVRecord> data) throws BeckedeFileException {

        if (headers == null || headers.isEmpty()) {
            throw new BeckedeFileException("No headers present!");
        }

        try {

            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File(path), true), StandardCharsets.UTF_8)
            );

            new CSVPrinter(bw, Constants.CSV_FORMAT
                    .withDelimiter(Constants.DELIMITER)
                    .withQuoteMode(QuoteMode.MINIMAL)
                    .withHeader(headers.keySet().toArray(new String[0]))
                    .withDelimiter(Constants.DELIMITER)
                    .withQuoteMode(QuoteMode.MINIMAL)
            ).printRecords(data);

            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(CsvWriterHandler.class.getName()).log(Level.SEVERE, null, ex);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
