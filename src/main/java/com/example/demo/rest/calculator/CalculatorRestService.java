package com.example.demo.rest.calculator;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorRestService {

    CalculatorState currentState = CalculatorState.INIT;
    int num1 = 0;
    int num2 = 0;
    String operation = "";
    float result = 0.0f;

    @Autowired
    private CalculatorRepository calculatorRepository;

    OperationDto calculate(float num1, float num2, String operation) {
        if (num1 > 1000 || num1 < -1000 || num2 > 1000 || num2 < -1000) {
            throw new IllegalArgumentException("num1 and num2 must be between -1000 and 1000");
        }
        float result = 0.0f;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("operation must be +, -, *, /");
        }
        String totalString = num1 + " " + operation + " " + num2 + " = " + result;
        this.calculatorRepository
                .save(OperationEntity.builder().num1(num1).num2(num2).operator(operation).result(result).build());
        return OperationDto.builder().num1(num1).num2(num2).operator(operation).result(result)
                .expressionString(totalString).build();
    }

    Iterable<OperationDto> getOperations() {
        return this.calculatorRepository.findAll().stream().map(entity -> OperationDto.builder()
                .num1(entity.getNum1())
                .num2(entity.getNum2())
                .operator(entity.getOperator())
                .result(entity.getResult())
                .expressionString(entity.getNum1() + " " + entity.getOperator() + " " + entity.getNum2() + " = "
                        + entity.getResult())
                .build()).collect(Collectors.toList());
    }

    OperationDto process(String expression) {
        // process each character in expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // check if is a number
            if (Character.isDigit(c)) {
                // process number as float
                int num = Integer.parseInt(String.valueOf(c));
                this.processNumber(num);
            } else {
                // check if there is a number before
                this.processSymbol(String.valueOf(c));
            }
        }
        if (currentState == CalculatorState.RESULT) {
            
            OperationDto op = OperationDto.builder().num1(num1).num2(num2).operator(operation).result(result)
                    .expressionString(num1 + " " + operation + " " + num2 + " = " + result).build();
            this.clearCalculator();
            return op;
        }
        this.clearCalculator();
        throw new IllegalArgumentException("Wrong expression format: " + expression);

    }

    void processNumber(int num) {
        switch (currentState) {
            case INIT:
                this.num1 = num;
                currentState = CalculatorState.FIRST_NUMBER;
                break;
            case FIRST_NUMBER:
                this.num1 = this.num1 * 10 + num;
                break;
            case SECOND_NUMBER:
                this.num2 = this.num2 * 10 + num;
                break;
            case RESULT:
                break;
            case ERROR:
                break;
            default:
                break;
        }
    }

    void processSymbol(String symbol) {
        switch (currentState) {
            case INIT:
                this.currentState = CalculatorState.ERROR;           
                break;
            case FIRST_NUMBER:
                if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")) {
                    this.operation = symbol;
                    currentState = CalculatorState.SECOND_NUMBER;
                }else {
                    currentState = CalculatorState.ERROR;
                }
                break;
            case SECOND_NUMBER:
                if (symbol.equals("=")) {
                    this.result = this.calculate(num1, num2, operation).getResult();
                    currentState = CalculatorState.RESULT;
                } else {
                    currentState = CalculatorState.ERROR;
                }
                break;
            case RESULT:
                break;
            case ERROR:
                break;
            default:
                break;
        }
    }

    void clearCalculator() {
        this.currentState = CalculatorState.INIT;
        this.num1 = 0;
        this.num2 = 0;
        this.operation = "";
        this.result = 0.0f;
    }
}
