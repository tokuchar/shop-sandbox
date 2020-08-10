package org.oncors.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long id) {
        super(String.format("Company with Id %d not found", id));
    }
}
