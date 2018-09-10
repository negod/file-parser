/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.fileutils;

import com.backede.fileutils.csv.reader.CsvReaderHandler;
import com.backede.fileutils.exception.BeckedeFileException;
import java.io.File;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class CsvReaderHandlerTest {

    @Test
    public void testReadFileWithHeaders() throws BeckedeFileException {
        File file = new File(CsvReaderHandlerTest.class.getResource("/test.csv").getPath());
        CsvReaderHandler handler = new CsvReaderHandler(file.getPath(), Boolean.TRUE);
        List<CSVRecord> listForTest = (List<CSVRecord>) handler.getRecords().get();
        assertEquals("ArraySize is not correct", 21, listForTest.size());
    }

    @Test
    public void testReadFileWithSingleRowAndHeaders() throws BeckedeFileException {
        File file = new File(CsvReaderHandlerTest.class.getResource("/Companies.csv").getPath());
        CsvReaderHandler handler = new CsvReaderHandler(file.getPath(), Boolean.TRUE);
        List<CSVRecord> listForTest = (List<CSVRecord>) handler.getRecords().get();
        assertEquals("ArraySize is not correct", 61, listForTest.size());
    }

    @Test
    public void testReadFileWithoutHeaders() throws BeckedeFileException {
        File file = new File(CsvReaderHandlerTest.class.getResource("/test.csv").getPath());
        CsvReaderHandler handler = new CsvReaderHandler(file.getPath(), Boolean.FALSE);
        List<CSVRecord> listForTest = (List<CSVRecord>) handler.getRecords().get();
        assertEquals("ArraySize is not correct", 22, listForTest.size());
    }

}
