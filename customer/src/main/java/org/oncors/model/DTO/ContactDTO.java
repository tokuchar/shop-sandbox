package org.oncors.model.DTO;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContactDTO {
    private long contactId;
    @NotNull(message = "email cannot be null")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email is not valid")
    private String email;
    @Digits(integer = 9, fraction = 0)
    private int phoneNumber;
}
