package org.oncors.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super(String.format("Company not found"));
    }
}
