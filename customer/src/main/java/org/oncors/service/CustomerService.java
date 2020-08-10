package org.oncors.service;

import org.oncors.exception.CustomerNotFoundException;
import org.oncors.exception.DataNotFoundException;
import org.oncors.model.Customer;
import org.oncors.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> companies = customerRepository.findAll();
        if (companies.isEmpty())
            throw new DataNotFoundException();
        return new ResponseEntity<>(
                companies,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Customer> findById(Long id) {
        return new ResponseEntity<>(
                customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id)),
                HttpStatus.OK
        );
    }

    public ResponseEntity<Customer> deleteById(Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    public ResponseEntity<Customer> update(Long id, Customer Customer) {
        return null;
    }

    public ResponseEntity<Customer> create(Customer Customer) {
        return new ResponseEntity<>(
                customerRepository.save(Customer),
                HttpStatus.CREATED
        );
    }
}
