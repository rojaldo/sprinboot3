package com.example.demo.rest.beers.units;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BeerUnitsRepository extends JpaRepository<BeerUnitsEntity, Long> {
    BeerUnitsEntity findById(long id);
}
