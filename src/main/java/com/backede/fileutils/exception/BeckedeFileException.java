/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backede.fileutils.exception;

/**
 *
 * @author Joakim Johansson ( joakimjohansson@outlook.com )
 */
public class BeckedeFileException extends Exception {

    public BeckedeFileException(String message) {
        super(message);
    }

    public BeckedeFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
