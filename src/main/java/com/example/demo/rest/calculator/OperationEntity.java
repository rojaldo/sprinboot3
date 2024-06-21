package com.example.demo.rest.calculator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_1", nullable = false, length = 1000, precision = 2)
    float num1;

    @Column(name = "num_2", nullable = false, length = 1000, precision = 2)
    float num2;

    @Column(name = "operator", nullable = false, length = 1)
    @Pattern(regexp = "[+\\-*/]")
    String operator;

    @Column(name = "result", nullable = false, length = 1000000, precision = 2)
    float result;

    @Column(name = "expression_string", nullable = false, length = 1000000)
    @Transient
    String expressionString;
}
