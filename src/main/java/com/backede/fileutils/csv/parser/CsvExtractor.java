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

    private final CsvImporter<T> importer;
    private final String filePath;
    private final Boolean hasHeaders;
    private Optional<List<CsvRecordWrapper>> records;

    public static final Boolean CSV_FILE_HA_HEADERS = Boolean.TRUE;
    public static final Boolean CSV_FILE_HAS_NOT_HEADERS = Boolean.FALSE;

    public CsvExtractor(CsvImporter<T> importer, String filePath, Boolean hasHeaders) {
        this.importer = importer;
        this.filePath = filePath;
        this.hasHeaders = hasHeaders;

        try {
            records = importer.modifyCsvRecords(importer.getCsvRecords(filePath, hasHeaders));
        } catch (BeckedeFileException ex) {
            log.error("Error when importing CsvRecords", ex);
        }

    }

    public void executeLogic(Consumer<T> valueConsumer) {

        records.ifPresent((List<CsvRecordWrapper> csvRecords) -> {
            importer.executeLogic(csvRecords).ifPresent(valueConsumer);
        });

    }

}
