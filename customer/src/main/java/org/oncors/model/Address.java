package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String zipCode;
    private String houseNumber;
}