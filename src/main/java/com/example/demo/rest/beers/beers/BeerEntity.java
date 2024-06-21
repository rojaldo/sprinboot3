package com.example.demo.rest.beers.beers;


import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.example.demo.rest.beers.units.BeerUnitsEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
// import table
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beers")
public class BeerEntity{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    @NonNull
    private String name;
    @NonNull
    private String tagline;

    @NonNull
    @Column(name = "first_brewed")
    private String firstBrewed;

    @NonNull
    // long text
    @Column(columnDefinition="TEXT")
    private String description;

    @NonNull
    @JsonProperty("image_url")
    @Column(name = "url")
    private String imageUrl;

    @NonNull
    @Range(min = 0, max = 100)
    private double abv;

    @NonNull
    @Min(0)
    private double ibu;

    @NonNull
    @Min(0)
    private double ebc;

    @OneToOne(mappedBy = "beer")
    private BeerUnitsEntity beerUnits;


}


