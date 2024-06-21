package com.example.demo.rest.beers.beers;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeersRepository extends JpaRepository<BeerEntity, Long> {

    BeerEntity findById(long id);
    BeerEntity findByName(String name);
    // find by abv by range
    List<BeerEntity> findByAbvBetween(double abv1, double abv2);
    // find by ibu by range
    List<BeerEntity> findByIbuBetween(double ibu1, double ibu2);
    // find by ebc by range
    List<BeerEntity> findByEbcBetween(double ebc1, double ebc2);
    //find by name non case sensitive
    List<BeerEntity> findByNameContaining(String name);
    //find by description
    List<BeerEntity> findByDescriptionContaining(String description);

}
