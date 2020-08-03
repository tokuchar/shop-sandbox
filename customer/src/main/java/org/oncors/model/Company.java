package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private String companyName;
    private String nip;
    private Address address;
}
