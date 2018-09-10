/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.fileutils.nordeaparser;

import com.backede.fileutils.csv.parser.CsvRecordWrapper;
import com.backede.fileutils.csv.parser.Normalizer;
import com.backede.fileutils.csv.reader.CsvReaderHandler;
import com.backede.fileutils.csv.reader.CsvWriterHandler;
import com.backede.fileutils.exception.BeckedeFileException;
import com.negod.fileutils.mock.MockEnumConstant;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class NormalizerTest {

    Iterable<CSVRecord> INPUT_RECORDS;
    LinkedHashMap<String, Integer> HEADERS;
    String INPUT_FILE_NAME = "test2.csv";
    String OUTPUT_FILE_NAME = "test2_write.csv";
    CsvWriterHandler CSV_WRITER;

    public NormalizerTest() {
    }

    @Before
    public void setUp() throws BeckedeFileException {
        File file = new File(getInputPath());
        CsvReaderHandler handler = new CsvReaderHandler(file.getPath(), Boolean.TRUE);
        INPUT_RECORDS = handler.getRecords().get();
        HEADERS = handler.getHeaderMap().get();
        CSV_WRITER = new CsvWriterHandler(getOutputPath());
    }

    /**
     * Test of removePeriod method, of class Normalizer.
     */
    @Test(expected = BeckedeFileException.class)
    public void testNormalizePeriod() throws IOException, BeckedeFileException {
        System.out.println("normalizePeriod");

        Normalizer instance = new Normalizer(INPUT_RECORDS, Optional.ofNullable(HEADERS));
        Optional<List<CsvRecordWrapper>> build = instance
                .removePeriod(MockEnumConstant.BELOPP_COLUMN)
                .removePeriod(MockEnumConstant.SALDO_COLUMN)
                .build();

        Boolean writeScv = CSV_WRITER.writeScvWrapper(null, build.get());

        if (writeScv) {

        } else {
            fail("Could not write file!");
        }

    }

    /**
     * Test of removePeriod method, of class Normalizer.
     */
    @Test()
    public void testNormalizePeriod_with_headers() throws IOException, BeckedeFileException {
        System.out.println("normalizePeriod");

        Normalizer instance = new Normalizer(INPUT_RECORDS, Optional.ofNullable(HEADERS));
        Optional<List<CsvRecordWrapper>> build = instance
                .removePeriod(MockEnumConstant.BELOPP_COLUMN)
                .removePeriod(MockEnumConstant.SALDO_COLUMN)
                .build();

        Boolean writeScv = CSV_WRITER.writeScvWrapper(HEADERS, build.get());

        if (writeScv) {

        } else {
            fail("Could not write file!");
        }

    }

    @Test()
    public void testNormalizeWord_with_headers() throws IOException, BeckedeFileException {
        System.out.println("normalizePeriod");

        Optional<List<CsvRecordWrapper>> build = new Normalizer(INPUT_RECORDS, Optional.ofNullable(HEADERS))
                .removePeriod(MockEnumConstant.BELOPP_COLUMN)
                .removePeriod(MockEnumConstant.SALDO_COLUMN)
                .removeWord(MockEnumConstant.TRANSACTION_COLUMN, "Kortköp")
                .removeWord(MockEnumConstant.RESERVATION_COLUMN, "Kortköp")
                .removeLeadingSpaces(MockEnumConstant.TRANSACTION_COLUMN)
                .removeWordStartingWith(MockEnumConstant.TRANSACTION_COLUMN, "18", 7)
                .removeTrailingAndLeadingSpaces(MockEnumConstant.TRANSACTION_COLUMN)
                .build();

        Boolean writeScv = CSV_WRITER.writeScvWrapper(HEADERS, build.get());

        if (writeScv) {

        } else {
            fail("Could not write file!");
        }

    }

    private String getInputPath() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        return resourceDirectory.toString().concat("/").concat(INPUT_FILE_NAME);
    }

    private String getOutputPath() {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        return resourceDirectory.toString().concat("/").concat(OUTPUT_FILE_NAME);
    }

}
