package com.example.demo.rest.calculator;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.calculator.CalculatorService;

import jakarta.el.Expression;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import org.aspectj.weaver.ast.Instanceof;
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
    public ResponseEntity<IOprationResponse> putMethodName(@RequestBody Map<String, Object> request) {
        OperationDto res = new OperationDto();
        // check calculatorDto is CalculatorDto Object
        // get expression field from request
        String expression = (String) request.get("expression");
        if (expression != null) {
            // call process method
            res = this.calculatorService.process(expression);
            // return operation=result
            return ResponseEntity.ok(res);
        }else {
            // get num1, num2, operation fields from request
            double num1 = (double) request.get("num_1");
            double num2 = (double) request.get("num_2");
            String operation = (String) request.get("operator");
            // call calculate method
            res = this.calculatorService.calculate( (float) num1, (float) num2, operation);
            // return operation=result
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("operations")
    public ResponseEntity<Iterable<OperationDto>> getMethodName() {
        // return new ResponseEntity <Iterable<OperationDto>>(this.calculatorService.getOperations(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(this.calculatorService.getOperations());
    }
    
}
