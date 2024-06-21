package com.example.demo.rest.beers.units;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.rest.beers.beers.BeerDto;
import com.example.demo.rest.beers.beers.BeerEntity;
import com.example.demo.rest.beers.beers.BeersRepository;

@Service
public class BeerUnitsService {

    @Autowired
    private BeerUnitsRepository beerUnitsRepository;

    @Autowired
    private BeersRepository beersRepository;

    public Iterable<BeerUnitsDto> getUnits() {
        List<BeerUnitsEntity> beerUnits = beerUnitsRepository.findAll();
        List<BeerUnitsDto> beerUnitsDto = new ArrayList<BeerUnitsDto>();
        for (BeerUnitsEntity beerUnit : beerUnits) {
            BeerEntity beer = beersRepository.findById(beerUnit.getBeer().getId());
            beerUnitsDto.add(BeerUnitsDto.builder()
                    .quantity(beerUnit.getQuantity())
                    .beer(BeerDto.builder()
                            .name(beer.getName())
                            .description(beer.getDescription())
                            .tagline(beer.getTagline())
                            .firstBrewed(beer.getFirstBrewed())
                            .imageUrl(beer.getImageUrl())
                            .abv(beer.getAbv())
                            .ebc(beer.getEbc())
                            .ibu(beer.getIbu())
                            .build())
                    .build());
        }
        return beerUnitsDto;
    }

    public BeerUnitsDto postUnits(BeerUnitsRequest beerUnitsRequest) {
        BeerEntity beer = beersRepository.findById(beerUnitsRequest.getBeerId());
        if(beer != null) {
            BeerUnitsEntity beerUnitsEntity = BeerUnitsEntity.builder()
                    .quantity(beerUnitsRequest.getQuantity())
                    .beer(beer)
                    .build();
            beerUnitsRepository.save(beerUnitsEntity);
            return BeerUnitsDto.builder()
                    .quantity(beerUnitsRequest.getQuantity())
                    .beer(BeerDto.builder()
                            .name(beer.getName())
                            .description(beer.getDescription())
                            .tagline(beer.getTagline())
                            .firstBrewed(beer.getFirstBrewed())
                            .imageUrl(beer.getImageUrl())
                            .abv(beer.getAbv())
                            .ebc(beer.getEbc())
                            .ibu(beer.getIbu())
                            .build())
                    .build();
        }else {
            return null;
        }
    }

    public BeerUnitsDto putUnits(QuantityRequest quantityRequest, Long id) {
        BeerUnitsEntity beerUnits = beerUnitsRepository.findById(id).get();
        System.out.println("BeerUnits: " + beerUnits);
        if( beerUnits != null) {
            
            beerUnits.setQuantity(quantityRequest.getQuantity());
            beerUnitsRepository.save(beerUnits);
            return BeerUnitsDto.builder()
                    .quantity(quantityRequest.getQuantity())
                    .beer(BeerDto.builder()
                            .name(beerUnits.getBeer().getName())
                            .description(beerUnits.getBeer().getDescription())
                            .tagline(beerUnits.getBeer().getTagline())
                            .firstBrewed(beerUnits.getBeer().getFirstBrewed())
                            .imageUrl(beerUnits.getBeer().getImageUrl())
                            .abv(beerUnits.getBeer().getAbv())
                            .ebc(beerUnits.getBeer().getEbc())
                            .ibu(beerUnits.getBeer().getIbu())
                            .build())
                    .build();
        }else {
            return null;
        }
        
    }
    
}
