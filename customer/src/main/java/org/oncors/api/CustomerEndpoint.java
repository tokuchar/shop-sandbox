package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.Company;
import org.oncors.model.Customer;
import org.oncors.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/customers")
public class CustomerEndpoint {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> postCustomer(@Valid @RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @PutMapping
    public ResponseEntity<Customer> putCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        throw new NotImplementedException();
    }

    @DeleteMapping
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteById(id);
    }
}
