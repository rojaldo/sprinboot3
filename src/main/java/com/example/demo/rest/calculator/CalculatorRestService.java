package com.example.demo.rest.calculator;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorRestService {

    @Autowired
    private CalculatorRepository calculatorRepository;

    OperationDto calculate(float num1, float num2, String operation){
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
        this.calculatorRepository.save(OperationEntity.builder().num1(num1).num2(num2).operator(operation).result(result).build());
        return OperationDto.builder().num1(num1).num2(num2).operator(operation).result(result).expressionString(totalString).build();
    }

    Iterable<OperationDto> getOperations(){
        return this.calculatorRepository.findAll().stream().map(entity -> 
        OperationDto.builder()
        .num1(entity.getNum1())
        .num2(entity.getNum2())
        .operator(entity.getOperator())
        .result(entity.getResult()).expressionString(entity.getNum1() + " " + entity.getOperator() + " " + entity.getNum2() + " = " + entity.getResult())
        .build()).collect(Collectors.toList());
    }
}
