/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@RequiredArgsConstructor
@Getter
@Slf4j
public class CsvRecordWrapper {

    private final CSVRecord record;

    public Optional<String> getColumn(CsvColumn column) {
        try {
            return Optional.ofNullable(record.get(column.getColumnName()));
        } catch (Exception e) {
            log.error("Error when getting column {}", column.getColumnName(), e);
        }
        return Optional.empty();
    }

}
