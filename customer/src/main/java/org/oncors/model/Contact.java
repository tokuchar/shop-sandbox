package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
public class Contact {
    @Email(message = "email is not valid")
    private String email;

    @Digits(integer = 9, fraction = 0)
    private int phoneNumber;
}
