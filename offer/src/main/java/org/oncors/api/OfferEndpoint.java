package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.Offer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/offers")
public class OfferEndpoint {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Offer>> getOffers() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Offer> getOffer(@PathVariable long id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Offer> postOffer(@RequestBody Offer offer) {
        throw new UnsupportedOperationException();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Offer> putOffer(@PathVariable long id, @RequestBody Offer offer) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteOffer(@PathVariable long id) {
        throw new UnsupportedOperationException();
    }

}
