/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.fileutils;

import com.backede.fileutils.exception.BeckedeFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */

@Ignore
public class CsvWriterHandlerTest {

    Set<Map<String, String>> INPUT_DATA;
    LinkedHashMap<String, Integer> HEADERS;
    String FILE_NAME = "test_write_headers.csv";

    public CsvWriterHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        INPUT_DATA = new LinkedHashSet<>();
//
//        
//        Map<String, String> record1 = new HashMap<>();
//
//        INPUT_DATA.add("author1|title1|1,2");
//        INPUT_DATA.add("author2|title2|3,4");
//        INPUT_DATA.add("author3|title3|5,6");
//        INPUT_DATA.add("author4|title4|7,8");
//
//        HEADERS = new LinkedHashMap<>();
//        HEADERS.put("Author", 1);
//        HEADERS.put("Title", 2);
//        HEADERS.put("Sum", 3);
    }

    /**
     * Test of writeScv method, of class CsvWriterHandler.
     */
    @Test(expected = BeckedeFileException.class)
    public void testWriteScv_without_headings() throws FileNotFoundException, IOException, InterruptedException, BeckedeFileException {
//        System.out.println("writeScv_no headers");
//
//        //Write the file
//        CsvWriterHandler instance = new CsvWriterHandler(getPath(FILE_NAME));
//        CsvRecordBuilder.
//        Boolean writeScv = instance.writeScv(null, INPUT_DATA);
//        //Assert file exists ( should throw exception otherwize )
//        String filePath = getPath(FILE_NAME);
//        deleteFile(filePath);

    }

    /**
     * Test of writeScv method, of class CsvWriterHandler.
     */
    @Test
    public void testWriteScv_with_headings() throws IOException, BeckedeFileException {
//        System.out.println("writeScv with headers");
//
//        CsvWriterHandler instance = new CsvWriterHandler(getPath(FILE_NAME));
//
//        List<CSVRecord> records = new ArrayList<>();
//
//        for (String string : INPUT_DATA) {
//            Reader reader = new StringReader(string);
//            records.addAll(CSVParser.parse(string, Constants.CSV_FORMAT).getRecords());
//        }
//
//        instance.writeScv(HEADERS, records);
//
//        StringBuilder stringBuffer = new StringBuilder();
//        for (String string : HEADERS.keySet()) {
//            stringBuffer.append(string).append(Constants.DELIMITER);
//        }
//
//        StringBuilder deleteCharAt = stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(Constants.DELIMITER.toString()));
//        INPUT_DATA.add(stringBuffer.toString());
//
//        assertData(getPath(FILE_NAME), INPUT_DATA);
    }

    private void assertData(String filePath, Set<String> assertionData) throws FileNotFoundException, IOException {

        //Assert file exists ( should throw exception otherwize )
        File file = new File(filePath);
        assertTrue(file.exists());

        FileReader fileReader = new FileReader(file.getPath());
        BufferedReader br = new BufferedReader(fileReader);

        //Assert data
        String data;
        List<String> assertData = new ArrayList<>();
        while ((data = br.readLine()) != null) {
            System.out.println(data);
            assertTrue("Text does not exist in dataFile", assertionData.contains(data));
        }

        fileReader.close();
        br.close();

        boolean delete = file.delete();
        assertTrue(delete);
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }

    private String getPath(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources");
        return resourceDirectory.toString().concat("/").concat(fileName);
    }

}
