package com.example.demo.rest.calculator;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CalculatorDto {

    @NotNull
    @Min(-1000)
    @Max(1000)
    @JsonAlias("num_1")
    public float num1;

    @NotNull
    @Min(-1000)
    @Max(1000)
    @JsonAlias("num_2")
    public float num2;

    @NotNull
    // must be +, -, *, /
    @Pattern(regexp = "[+\\-*/]")
    @JsonAlias("operator")
    public String operation;
    
}
