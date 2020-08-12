package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.model.Company;
import org.oncors.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/companies")
public class CompanyEndpoint {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Company> postCompany(@Valid @RequestBody Company company) {
        return companyService.create(company);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Company> putCompany(@PathVariable Long id, @Valid @RequestBody Company company) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Company> deleteCompany(@PathVariable long id) {
        return companyService.deleteById(id);
    }

}
