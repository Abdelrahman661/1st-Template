package com.unitconverter.exception;

/**
 * Custom exception for invalid unit conversions
 */
public class InvalidUnitException extends RuntimeException {

    public InvalidUnitException(String message) {
        super(message);
    }

    public InvalidUnitException(String message, Throwable cause) {
        super(message, cause);
    }
}