package com.karzhou.fraction.exception;

public class FractionFileReadException extends Exception {
    public FractionFileReadException(String message) {
        super(message);
    }

    public FractionFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
