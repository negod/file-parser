/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import com.backede.fileutils.csv.reader.CsvRecordBuilder;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Slf4j
public class Normalizer {

    Set<Map<String, String>> normalizedRecords;
    Optional<LinkedHashMap<String, Integer>> headers = Optional.empty();

    //^[0-9]*$
    // eg: BETALNING PG 3783601-8 NORDEA GOLD
    private static final Pattern ACCOUNT_TRANSFER = Pattern.compile("[0-9 ]{1,50}-[0-9]{1,50}");
    // eg: 2350 51 23456
    private static final Pattern ACCOUNT_NUMBER = Pattern.compile("[0-9]{4} [0-9]{2} [0-9]{5}");

    //eg: alla ord som har 3 siffor i sig
    private static final Pattern NUMBERS_IN_NAME = Pattern.compile("\\w*[0-9]{3}\\w*");

    private static final Pattern MORE_THAN_ONE_SPACE = Pattern.compile("([ ]{2,})");

    public Normalizer(Iterable<CSVRecord> records, Optional<LinkedHashMap<String, Integer>> headers) {
        normalizedRecords = CsvRecordBuilder.normalizeRecords(records);
        this.headers = headers;
    }

    public Normalizer copyValueToNewColumn(CsvColumn newColumnName, CsvColumn originalValueColumn) {
        normalizedRecords.forEach((record) -> {
            if (record.get(originalValueColumn.getColumnName()) != null) {
                String originalValue = record.get(originalValueColumn.getColumnName());
                record.put(newColumnName.getColumnName(), originalValue);

                headers.ifPresent(headers -> headers.put(newColumnName.getColumnName(), headers.size() + 1));

            }
        });

        return this;
    }

    private String extractName(String name) {
        Matcher numbersInName = NUMBERS_IN_NAME.matcher(name);

        if (numbersInName.find()) {
            return numbersInName.replaceAll("").trim();
        }

        return name.trim();
    }

    public Normalizer extractNameFromColumn(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String fixedString = record.get(column.getColumnName());
                record.put(column.getColumnName(), extractName(fixedString));
            }
        });
        return this;
    }

    private String trimSpaces(String inData) {
        Matcher spacesInName = MORE_THAN_ONE_SPACE.matcher(inData);
        return spacesInName.replaceAll(" ").trim();
    }

    public Normalizer trimSpacesFromColumn(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            String fixedString = record.get(column.getColumnName());
            record.put(column.getColumnName(), trimSpaces(fixedString));
        });
        return this;
    }

    public Normalizer capitalizeAll(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).toUpperCase();
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removeWordStartingWith(CsvColumn column, String startingWith, Integer numberOfCharsToDelete) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null && record.get(column.getColumnName()).startsWith(startingWith)) {
                String replaced = record.get(column.getColumnName()).substring(numberOfCharsToDelete, record.get(column.getColumnName()).length());
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removeMinus(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).replaceAll("-", "");
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removeLeadingSpaces(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).replaceAll("^\\s+", "");
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removeTrailingAndLeadingSpaces(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).trim();
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removeWord(CsvColumn column, String word) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).replaceAll(word.toUpperCase(), "");
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer removePeriod(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            if (record.get(column.getColumnName()) != null) {
                String replaced = record.get(column.getColumnName()).replace(".", "");
                record.put(column.getColumnName(), replaced);
            }
        });
        return this;
    }

    public Normalizer replacePeriodWithComma(CsvColumn column) {
        normalizedRecords.forEach((record) -> {
            try {
                if (record.get(column.getColumnName()) != null) {
                    String replaced = record.get(column.getColumnName()).replace(",", ".");
                    record.put(column.getColumnName(), replaced);
                }
            } catch (NullPointerException | IllegalArgumentException e) {
                log.error("Failure when replacing comma for field {}", column.getColumnName());
            }
        });
        return this;
    }

    public Optional<List<CsvRecordWrapper>> build() {
        try {
            return Optional.ofNullable(CsvRecordBuilder.build(normalizedRecords, headers.get()));
        } catch (IOException ex) {
            log.error("Error when building CSVRecords {}", ex.getStackTrace());
        }
        return Optional.empty();
    }

}
