package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Address {
    @NotNull(message = "City cannot by null")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "city cannot contain numbers")
    private String city;

    @NotNull(message = "street cannot by null")
    private String street;

    @Pattern(regexp = "^\\d{2}-\\d{3}", message = "valid zip code format is xx-xxx")
    private String zipCode;

    @NotNull(message = "house number cannot by null")
    private String houseNumber;
}