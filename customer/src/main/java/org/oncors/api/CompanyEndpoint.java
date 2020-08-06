package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/companies")
public class CompanyEndpoint {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Company>> getCompanies() {
        throw new NotImplementedException();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Company> getCompany(@PathVariable long id) {
        throw new NotImplementedException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Company> postCompany(@RequestBody Company company) {
        throw new NotImplementedException();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Company> putCompany(@PathVariable long id, @RequestBody Company company) {
        throw new NotImplementedException();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCompany(@PathVariable long id) {
        throw new NotImplementedException();
    }

}
