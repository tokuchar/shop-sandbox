package org.oncors.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.oncors.exception.CompanyNotFoundException;
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
    private ObjectMapper objectMapper = new ObjectMapper();

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

//    @PutMapping
//    public ResponseEntity<Company> alterCompany(@PathVariable Long id, @Valid @RequestBody Company newCompany) {
//        Company company = companyRepository.findById(id).get();
//        company=newCompany;
//
//    }

    @PatchMapping(path="/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody JsonPatch patch) {
       try {
           Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
           company = applyPatchToCustomer(patch, company);
           return ResponseEntity.ok(company);
       } catch (JsonPatchException | JsonProcessingException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       } catch (CompanyNotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

   /* @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable long id) {
        return companyRepository.deleteById(id);
    }*/

    @Autowired
    public CompanyEndpoint(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private Company applyPatchToCustomer(JsonPatch patch, Company targetCustomer) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, Company.class);
    }
}
