package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.aop.Trace;
import org.oncors.model.Customer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/customers")
public class CustomerEndpoint {

    @Trace
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers(@RequestHeader HttpHeaders httpHeaders) {
        throw new NotImplementedException();
    }

    @Trace
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomer(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id) {
        throw new NotImplementedException();
    }

    @Trace
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> postCustomer(@RequestHeader HttpHeaders httpHeaders, @RequestBody Customer Customer) {
        throw new NotImplementedException();
    }

    @Trace
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Customer> putCustomer(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id, @RequestBody Customer customer) {
        throw new NotImplementedException();
    }

    @Trace
    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id) {
        throw new NotImplementedException();
    }

}
