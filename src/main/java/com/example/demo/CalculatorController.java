package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//https://api.punkapi.com/v2/beers?abv_gt=3&abv_lt=5&ibu_gt=0

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String getMethodName(
        @RequestParam(name = "num1", required = false, defaultValue = "0.0") float param1,
        @RequestParam(name = "num2", required = false, defaultValue = "0.0") float param2,
        @RequestParam(name = "operation", required = false, defaultValue = "add") String operation,
        Model view){
            view.addAttribute("num1", param1);
            view.addAttribute("num2", param2);
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
