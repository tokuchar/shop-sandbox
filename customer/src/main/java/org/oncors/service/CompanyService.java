//package org.oncors.service;
//
//import org.modelmapper.ModelMapper;
//import org.oncors.model.DTO.CompanyDTO;
//import org.oncors.model.entity.Company;
//import org.oncors.repository.CompanyRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CompanyService {
//    @Autowired
//    private CompanyRepository companyRepository;
//    private ModelMapper mapper = new ModelMapper();
//
//
//    public List<Company> getAll() {
//    return companyRepository.findAll();
////        if (companies.isEmpty())
////            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(companies);
//
//    //    return ResponseEntity.status(HttpStatus.FOUND).body(companies);
//    }
//
//    public CompanyDTO getCompanyById(Long id){
//
//
//    }
//
//    private CompanyDTO convertToOrderDto(Company company) {
//        CompanyDTO companyDTO = mapper.map(company, CompanyDTO.class);
//        return companyDTO;
//    }
//}
