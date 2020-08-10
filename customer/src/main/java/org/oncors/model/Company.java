package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Company {
    private String login;
    @NotNull(message = "company name cannot be null")
    @NotEmpty(message = "company name cannot be empty")
    private String companyName;

    @NotNull(message = "nip name cannot be empty")
    @Digits(integer = 10, fraction = 0, message = "np should contain only 10 digits")
    private Integer nip;

    @Valid
    @NotNull(message = "address cannot be null")
    private Address address;

    @Valid
    @NotNull(message = "Contact cannot be null")
    private Contact contact;
}
