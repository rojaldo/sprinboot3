package com.example.demo.rest.calculator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<OperationEntity, Long> {
    
}
