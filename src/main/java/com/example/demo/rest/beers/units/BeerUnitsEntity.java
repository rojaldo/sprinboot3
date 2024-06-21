package com.example.demo.rest.beers.units;

import com.example.demo.rest.beers.beers.BeerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "beer_units")
public class BeerUnitsEntity {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "beer_id")
    private BeerEntity beer;
}
