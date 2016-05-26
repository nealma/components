package com.nealma.framework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by nealpc on 5/27/16.
 */
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(InvalidRequestIdException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleInvalidRequestError(InvalidRequestIdException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleUnexpectedServerError(RuntimeException ex) {
        return ex.getMessage();
    }
}
