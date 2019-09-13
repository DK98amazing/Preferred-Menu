package com.prefrred.exception.myenum;

public class SelectException extends RuntimeException {

    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectException(String message) {
        super(message);
    }
}
