package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Customer {
    private String login;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Address address;
    private  Contact contact;
}