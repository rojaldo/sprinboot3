package com.example.demo.rest.calculator;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.calculator.CalculatorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1")
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;
    
    @PutMapping("evaluate")
    public ResponseEntity<Map<String,String>> putMethodName(@RequestBody @Valid CalculatorDto calculatorDto) {
        
        float result = this.calculatorService.calculate(calculatorDto.num1, calculatorDto.num2, calculatorDto.operation);
        // return operation=result
        return ResponseEntity.ok(Map.of("result", String.valueOf(calculatorDto.num1) + " " + calculatorDto.operation + " " + String.valueOf(calculatorDto.num2) + " = " + String.valueOf(result)));
    }
}
