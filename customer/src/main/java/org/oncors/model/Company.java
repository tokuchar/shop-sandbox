package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private String login;
    private String companyName;
    private String taxNumber;
    private Address address;
    private Contact contact;
}
