package com.example.demo;

import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleException(Exception e) {
        // return stack trace
        return Map.of ("message", e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException e) {
        return Map.of ("message", e.getMessage());
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, String> handleInternalServerError(InternalServerError e) {
        return Map.of ("message", e.getMessage());
    }

    // 404 
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleNotFound(org.springframework.web.client.HttpClientErrorException.NotFound e) {
        return Map.of ("message", "404: " + e.getMessage());
    }
}
