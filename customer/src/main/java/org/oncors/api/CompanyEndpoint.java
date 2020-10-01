package org.oncors.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.oncors.dto.CompanyDTO;
import org.oncors.dto.CompanyWithEmployeesDTO;
import org.oncors.exception.CompanyNotFoundException;
import org.oncors.model.Company;
import org.oncors.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@EnableSwagger2
@RequestMapping("/companies")
public class CompanyEndpoint {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper = configureMapper();

    @GetMapping
    public ResponseEntity<List<CompanyWithEmployeesDTO>> getCompaniesInfoWithEmployees() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(companies.stream().map(value -> mapper.map(value, CompanyWithEmployeesDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping("WithoutEmployees")
    public ResponseEntity<List<CompanyDTO>> getCompaniesInfo() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.ok().body(companies.stream().map(value -> mapper.map(value, CompanyDTO.class)).collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompanyInfo(@PathVariable Long id) {
        try {
            Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
            CompanyDTO companyDTO = mapper.map(company, CompanyDTO.class);
            return ResponseEntity.ok(companyDTO);
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("WithEmployees/{id}")
    public ResponseEntity<CompanyWithEmployeesDTO> getCompanyWithEmployees(@PathVariable Long id) {
        try {
            Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
            CompanyWithEmployeesDTO companyDTO = mapper.map(company, CompanyWithEmployeesDTO.class);
            return ResponseEntity.ok(companyDTO);
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping
    public ResponseEntity<Company> postCompany(@Valid @RequestBody CompanyDTO newCompany) {
        Company company = mapper.map(newCompany, Company.class);
        return ResponseEntity.status(HttpStatus.OK).body(companyRepository.save(company));
    }

    @PutMapping
    public ResponseEntity<Company> alterCompany(@PathVariable Long id, @Valid @RequestBody CompanyDTO newCompanyDTO) {
        try {
            Company newCompany = mapper.map(newCompanyDTO, Company.class);
            Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
            company.setCompanyName(newCompany.getCompanyName());
            company.setUsers(newCompany.getUsers());
            return ResponseEntity.ok(company);

        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Company> updateCompany(@PathVariable long id, @RequestBody JsonPatch patch) {
        try {
            Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
            company = applyPatchToCustomer(patch, company);
           // companyRepository.save(company);

            return ResponseEntity.ok(company);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable long id) {
        try {
            Company company = Optional.ofNullable(companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new)).get();
            companyRepository.delete(company);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();

        } catch (CompanyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Autowired
    public CompanyEndpoint(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    private ModelMapper configureMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    private Company applyPatchToCustomer(JsonPatch patch, Company targetCustomer) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, Company.class);
    }

}
