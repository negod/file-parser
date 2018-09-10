/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.negod.fileutils.mock;

import com.backede.fileutils.csv.parser.CsvColumn;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Joakim Backede ( joakim.backede@outlook.com )
 */
@Getter
@RequiredArgsConstructor
public enum MockEnumConstant implements CsvColumn {

    EMPTY_STRING_DATA(""),
    SALDO_COLUMN("Saldo"),
    TRANSACTION_COLUMN("Transaktion"),
    RESERVATION_COLUMN("Reservation"),
    BELOPP_COLUMN("Belopp");

    private final String columnName;

}
