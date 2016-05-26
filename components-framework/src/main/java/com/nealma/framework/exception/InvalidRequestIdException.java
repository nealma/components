package com.nealma.framework.exception;

/**
 * Created by nealpc on 5/27/16.
 */
public class InvalidRequestIdException extends Exception{
    public InvalidRequestIdException() {
    }

    public InvalidRequestIdException(String message) {
        super(message);
    }
}
