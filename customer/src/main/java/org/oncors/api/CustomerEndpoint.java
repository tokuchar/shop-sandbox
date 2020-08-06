package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.Company;
import org.oncors.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerEndpoint {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers() {
        throw new IllegalArgumentException("Not implemented!");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        throw new IllegalArgumentException("Not implemented!");
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer Customer) {
        throw new IllegalArgumentException("Not implemented!");
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Customer> putCustomer(@PathVariable long id, @RequestBody Customer customer) {
        throw new IllegalArgumentException("Not implemented!");
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@PathVariable long id) {
        throw new IllegalArgumentException("Not implemented!");

    }
}
