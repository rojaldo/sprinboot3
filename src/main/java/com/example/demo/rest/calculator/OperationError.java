package com.example.demo.rest.calculator;

public class OperationError implements IOprationResponse{
    private String message;

    public OperationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
