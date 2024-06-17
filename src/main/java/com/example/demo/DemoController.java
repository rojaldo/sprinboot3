package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DemoController {

    @GetMapping("/demo")
    public String getMethodName(@RequestParam(name="msg", required = false, defaultValue = "World!") String param, Model view) {
        view.addAttribute("message", param);
        return "mytemplate";
    }
    
    
}
