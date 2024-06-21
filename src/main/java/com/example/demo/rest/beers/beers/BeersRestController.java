package com.example.demo.rest.beers.beers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BeersRestController {

    @Autowired
    BeersService beersService;
    
    @GetMapping("/beers")
    public ResponseEntity<Iterable<BeerDto>> getBeers(
        @RequestParam(required = false, defaultValue = "0", name ="abv_gte") Double abvGte,
        @RequestParam(required = false, defaultValue = "100", name ="abv_lte") Double abvLte,
        @RequestParam(required = false, defaultValue = "0", name ="ibu_gte") Double ibuGte,
        @RequestParam(required = false, defaultValue = "100", name ="ibu_lte") Double ibuLte,
        @RequestParam(required = false, defaultValue = "0", name ="ebc_gte") Double ebcGte,
        @RequestParam(required = false, defaultValue = "100", name ="ebc_lte") Double ebcLte,
        @RequestParam(required = false, defaultValue = "", name ="name") String name,
        @RequestParam(required = false, defaultValue = "", name ="keyword") String keyWord
        ) {
        if (abvGte > 0 || abvLte < 100) {
            return ResponseEntity.status(200).body(this.beersService.getFilteredByAbvBeers(abvGte, abvLte));
        }else if (ibuGte > 0 || ibuLte < 100) {
            return ResponseEntity.status(200).body(this.beersService.getFilteredByIbuBeers(ibuGte, ibuLte));
        } else if (ebcGte > 0 || ebcLte < 100) {
            return ResponseEntity.status(200).body(this.beersService.getFilteredByEbcBeers(ebcGte, ebcLte));
        } else if (!name.isEmpty()) {
            return ResponseEntity.status(200).body(this.beersService.getFilteredByNameBeers(name));
        } else if (!keyWord.isEmpty()) {
            return ResponseEntity.status(200).body(this.beersService.getFilteredByKeywordBeers(keyWord));
        }
        return ResponseEntity.status(200).body(this.beersService.getAllBeers());
    }

}
