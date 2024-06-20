package com.example.demo.rest.calculator;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.calculator.CalculatorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/v1")
public class CalculatorRestController {

    @Autowired
    private CalculatorRestService calculatorService;
    
    @PutMapping("calculator")
    public ResponseEntity<IOprationResponse> putMethodName(@RequestBody @Valid CalculatorDto calculatorDto) {
        
        OperationDto res = this.calculatorService.calculate(calculatorDto.num1, calculatorDto.num2, calculatorDto.operation);
        if(res == null){
            return ResponseEntity.badRequest().body(new OperationError( "operation must be +, -, *, /"));
        }
        // return operation=result
        return ResponseEntity.ok(res);
    }

    @GetMapping("operations")
    public ResponseEntity<Iterable<OperationDto>> getMethodName() {
        // return new ResponseEntity <Iterable<OperationDto>>(this.calculatorService.getOperations(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(this.calculatorService.getOperations());
    }
    
}
