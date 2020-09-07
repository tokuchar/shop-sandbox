package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.aop.Trace;
import org.oncors.model.Company;
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
@RequestMapping("/companies")
public class CompanyEndpoint {

    @Trace
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Company>> getCompanies(@RequestHeader HttpHeaders httpHeaders) {
        throw new NotImplementedException();
    }

    @Trace
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Company> getCompany(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id) {
        throw new NotImplementedException();
    }

    @Trace
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Company> postCompany(@RequestHeader HttpHeaders httpHeaders, @RequestBody Company company) {
        throw new NotImplementedException();
    }

    @Trace
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Company> putCompany(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id, @RequestBody Company company) {
        throw new NotImplementedException();
    }

    @Trace
    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCompany(@RequestHeader HttpHeaders httpHeaders, @PathVariable long id) {
        throw new NotImplementedException();
    }

}
