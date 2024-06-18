package com.example.demo.calculator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CalculatorService {

    private ArrayList<String> previousOperations = new ArrayList<String>();
    
    float calculate(float num1, float num2, String operation){
        switch (operation) {
            case "add":
                return num1 + num2;
            case "subtract":
                return num1 - num2;
            case "multiply":
                return num1 * num2;
            case "divide":
                return num1 / num2;
            default:
                return num1 + num2;
        }
    }

    public ArrayList<String> getPreviousOperations() {
        return previousOperations;
    }

    public void addOperation(float param1, float param2, String operation, float result){
        this.previousOperations.add(param1 + " " + this.decodeOperation(operation) + " " + param2 + " = " + result);
    }

    public void clearOperations(){
        this.previousOperations.clear();
    }

    public void removeOperation(int index){
        this.previousOperations.remove(index);
    }

    private String decodeOperation(String operation){
        switch (operation) {
            case "add":
                return "+";
            case "subtract":
                return "-";
            case "multiply":
                return "*";
            case "divide":
                return "/";
            default:
                return "+";
        }
    }
}
