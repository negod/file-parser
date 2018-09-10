/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import com.backede.fileutils.exception.BeckedeFileException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
@Getter
public class CsvExtractor<T> {

    private final CsvImporter importer;
    private final String filePath;
    private final Boolean hasHeaders;
    private Optional<List<CsvRecordWrapper>> records;

    public static final Boolean CsvFileHasHeaders = Boolean.TRUE;
    public static final Boolean CsvFileHasNotHeaders = Boolean.FALSE;

    public CsvExtractor(CsvImporter importer, String filePath, Boolean hasHeaders) {
        this.importer = importer;
        this.filePath = filePath;
        this.hasHeaders = hasHeaders;

        try {
            Optional<Normalizer> csvRecords = importer.getCsvRecords(filePath, hasHeaders);
            records = importer.modifyCsvRecords(csvRecords);
        } catch (BeckedeFileException ex) {
            log.error("Error when importing CsvRecords", ex);
            Logger.getLogger(CsvExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            log.error("Error when importing CsvRecords", ex);
        }

    }

    public void executeLogic(Consumer<T> valueConsumer) {

        records.ifPresent(records -> {
            importer.executeLogic(records).ifPresent(valueConsumer);
        });

    }

}
