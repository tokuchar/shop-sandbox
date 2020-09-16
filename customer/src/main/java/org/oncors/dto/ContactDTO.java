package org.oncors.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ContactDTO {
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email is not valid")
    private String email;
    @Column(name = "phone")
    @Digits(integer = 9, fraction = 0)
    private int phoneNumber;
}
