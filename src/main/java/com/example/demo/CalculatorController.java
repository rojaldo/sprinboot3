package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//https://api.punkapi.com/v2/beers?abv_gt=3&abv_lt=5&ibu_gt=0

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    private String[] heroes = {"Batman", "Superman", "Spiderman"};

    @GetMapping("/calculator")
    public String getMethodName(
        @RequestParam(name = "num1", required = false, defaultValue = "0.0") float param1,
        @RequestParam(name = "num2", required = false, defaultValue = "0.0") float param2,
        @RequestParam(name = "operation", required = false, defaultValue = "add") String operation,
        Model view){
            view.addAttribute("num1", param1);
            view.addAttribute("num2", param2);
            float result = this.calculatorService.calculate(param1, param2, operation);
            view.addAttribute("result", result);
            view.addAttribute("heroes", heroes);
            switch (operation) {
                case "add":
                    view.addAttribute("operation", "+");
                    break;
                case "subtract":
                    view.addAttribute("operation", "-");
                    break;
                case "multiply":
                    view.addAttribute("operation", "*");
                    break;
                case "divide":
                    view.addAttribute("operation", "/");
                    break;
                default:
                    view.addAttribute("operation", "+");
                    break;
            }

        return "calculator";
    }


    
    
}
