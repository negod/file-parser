/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.fileutils.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
public class MockDAta {

    public static List<Set<Map<String, String>>> getRecords(String value, String... header) {

        List<Set<Map<String, String>>> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            
        }

        return null;

    }

    private Set<Map<String, String>> createRow(String value, String... header) {
        Set<Map<String, String>> row = new HashSet<>();
        for (Integer i = 0; i < header.length; i++) {
            row.add(createField(header[i], value.concat(i.toString())));
        }
        return row;
    }

    private Map<String, String> createField(String header, String value) {
        Map<String, String> row = new HashMap<>();
        row.put(header, value);
        return row;
    }

}
