package org.oncors.service;

import org.oncors.exception.CompanyNotFoundException;
import org.oncors.exception.DataNotFoundException;
import org.oncors.model.Company;
import org.oncors.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public ResponseEntity<List<Company>> findAll() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty())
            throw new DataNotFoundException();
        return new ResponseEntity<>(
                companies,
                HttpStatus.OK
        );
    }

//    public ResponseEntity<Company> findById(Long id) {
//        return new ResponseEntity<>(
//                companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id)),
//                HttpStatus.OK
//        );
//    }

    public ResponseEntity<Company> deleteById(Long id) {
        companyRepository.deleteById(id);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    public ResponseEntity<Company> update(Long id, Company company) {
        Company updateCompany = companyRepository.getOne(id);
        updateCompany.setCompanyName(company.getCompanyName());
        return null;
    }

    public ResponseEntity<Company> create(Company company) {
        return new ResponseEntity<>(
                companyRepository.save(company),
                HttpStatus.CREATED
        );
    }

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
}
