package com.example.demo.rest.calculator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationDto implements IOprationResponse{
    @NotNull
    float num1;

    @NotNull
    float num2;

    @NotBlank
    @Pattern(regexp = "[+\\-*/]")
    String operator;
    
    @NotNull
    float result;

    @NotBlank
    String expressionString;
}
