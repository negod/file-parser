/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import com.backede.fileutils.csv.reader.CsvReaderHandler;
import com.backede.fileutils.exception.BeckedeFileException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 * @param <T> The type to return from the execute method
 */
public interface CsvImporter<T> {

    public default Optional<Normalizer> getCsvRecords(String csvFilePath, Boolean hasHeaders) throws BeckedeFileException {
        CsvReaderHandler handler = new CsvReaderHandler(csvFilePath, hasHeaders);

        return handler.getRecords().map(records -> {
            return Optional.ofNullable(new Normalizer(records, handler.getHeaderMap()));
        }).orElse(Optional.empty());

    }

    public Optional<List<CsvRecordWrapper>> modifyCsvRecords(Optional<Normalizer> records) throws IOException;

    public Optional<T> executeLogic(List<CsvRecordWrapper> records);

}
