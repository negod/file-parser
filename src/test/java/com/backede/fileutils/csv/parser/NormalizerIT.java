/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.csv.parser;

import com.negod.fileutils.mock.MockEnumConstant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class NormalizerIT {

    public NormalizerIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public String testString(String data) {
        String[] splittedString = data.split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < splittedString.length; i++) {
            if (splittedString[i].trim().length() == 1) {
                if (i + 1 < splittedString.length) {
                    builder.append(splittedString[i].trim());
                    builder.append(splittedString[i += 1].trim());
                }
            } else {
                builder.append(" ").append(splittedString[i]);
            }
        }
        return builder.toString();
    }

    @Test
    public void test() {
        String data = "7ELEVEN";
        System.out.println(testString(data));
    }

    /**
     * Test of extractNameFromColumn method, of class Normalizer.
     */
    @Test
    public void testExtractName() {
        System.out.println("extractName");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.extractNameFromColumn(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeWordStartingWith method, of class Normalizer.
     */
    @Test
    public void testRemoveWordStartingWith() {
        System.out.println("removeWordStartingWith");
        String startingWith = "";
        Integer numberOfCharsToDelete = null;
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removeWordStartingWith(MockEnumConstant.EMPTY_STRING_DATA, startingWith, numberOfCharsToDelete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMinus method, of class Normalizer.
     */
    @Test
    public void testRemoveMinus() {
        System.out.println("removeMinus");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removeMinus(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLeadingSpaces method, of class Normalizer.
     */
    @Test
    public void testRemoveLeadingSpaces() {
        System.out.println("removeLeadingSpaces");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removeLeadingSpaces(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTrailingAndLeadingSpaces method, of class Normalizer.
     */
    @Test
    public void testRemoveTrailingAndLeadingSpaces() {
        System.out.println("removeTrailingAndLeadingSpaces");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removeTrailingAndLeadingSpaces(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeWord method, of class Normalizer.
     */
    @Test
    public void testRemoveWord() {
        System.out.println("removeWord");
        String word = "";
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removeWord(MockEnumConstant.EMPTY_STRING_DATA, word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePeriod method, of class Normalizer.
     */
    @Test
    public void testRemovePeriod() {
        System.out.println("removePeriod");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.removePeriod(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replacePeriodWithComma method, of class Normalizer.
     */
    @Test
    public void testReplaceComma() {
        System.out.println("replaceComma");
        Normalizer instance = null;
        Normalizer expResult = null;
        Normalizer result = instance.replacePeriodWithComma(MockEnumConstant.EMPTY_STRING_DATA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of build method, of class Normalizer.
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        LinkedHashMap<String, Integer> headers = null;
        Normalizer instance = null;
        Iterable<CsvRecordWrapper> expResult = null;
        Optional<List<CsvRecordWrapper>> result = instance.build();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
