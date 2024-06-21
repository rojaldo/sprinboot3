package com.example.demo.rest.beers.units;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerUnitsRestController {

    @Autowired
    BeerUnitsService beerUnitsService;

    @GetMapping("/units")
    public ResponseEntity<Iterable<BeerUnitsDto>> getUnits() {
        return ResponseEntity.status(200).body(this.beerUnitsService.getUnits());
    }

    @PostMapping("/units")
    public ResponseEntity<Object> postUnits(@RequestBody BeerUnitsRequest beerUnitsRequest ) {
        BeerUnitsDto beer =this.beerUnitsService.postUnits(beerUnitsRequest);
        if (beer != null) {
            return ResponseEntity.status(201).body(beer);
        }
        return ResponseEntity.status(400).body(Map.of("message", "Beer not found"));
    }

    @PutMapping("/units/{id}")
    public ResponseEntity<BeerUnitsDto> putUnits(@RequestBody QuantityRequest quantityRequest, @PathVariable long id) {
        return ResponseEntity.status(200).body(this.beerUnitsService.putUnits(quantityRequest, id));
    }
    
}
