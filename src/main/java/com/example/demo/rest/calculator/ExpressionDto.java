package com.example.demo.rest.calculator;

import com.fasterxml.jackson.annotation.JsonAlias;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpressionDto implements ICalculatorRequest{

    @NotBlank
    @JsonAlias("expression")
    String expression;
    
}
