package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private String login;
    private String companyName;
    private String nip;
    private Address address;
    private Contact contact;
}
