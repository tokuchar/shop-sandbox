package org.oncors.api;

import lombok.extern.slf4j.Slf4j;
import org.oncors.exception.DataNotFoundException;
import org.oncors.model.Company;
import org.oncors.repository.CompanyRepository;
import org.oncors.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/companies")
public class CompanyEndpoint {
    private final CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(companies);

        return ResponseEntity.status(HttpStatus.FOUND).body(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(company.get()));

    }

    @PostMapping
    public ResponseEntity<Company> postCompany(@Valid @RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.OK).body(companyRepository.save(company));
    }

    @PutMapping
    public ResponseEntity<Company> alterCompany(@PathVariable Long id, @Valid @RequestBody Company newCompany) {
        Optional<Company> company = companyRepository.findById(id);
        return companyRepository.findById(id).map(company1 -> {
            company1=newCompany;
            return ResponseEntity.status(HttpStatus.ACCEPTED);
        };
    }

    @PatchMapping
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody Company company) {
        throw new NotImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable long id) {
        return companyRepository.deleteById(id);
    }

    @Autowired
    public CompanyEndpoint(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
