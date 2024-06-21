package com.example.demo.rest.beers.beers;

import java.util.Set;

import org.hibernate.annotations.Synchronize;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class BeerDto {

    @NonNull
    private String name;
    @NonNull
    private String tagline;
    @NonNull
    private String description;
    @NonNull
    @JsonProperty("first_brewed")
    private String firstBrewed;
    @NonNull
    @JsonProperty("image_url")
    private String imageUrl;
    @NonNull
    private double abv;
    @NonNull
    private double ibu;
    @NonNull
    private double ebc;


    
}
