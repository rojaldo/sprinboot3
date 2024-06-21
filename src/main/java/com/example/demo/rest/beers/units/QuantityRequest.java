package com.example.demo.rest.beers.units;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class QuantityRequest {
    @Range(min = 0, max = 100000)
    private int quantity;
}
