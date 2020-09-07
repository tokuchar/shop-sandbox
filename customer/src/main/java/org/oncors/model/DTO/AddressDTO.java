package org.oncors.model.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AddressDTO {
    private Long addressId;
    @NotNull(message = "City cannot by null")
    @NotEmpty(message = "City cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "city cannot contain numbers")
    private String city;
    @NotNull(message = "Country cannot by null")
    @NotEmpty(message = "Country cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "country cannot contain numbers")
    private String country;
    @NotNull(message = "District cannot by null")
    @NotEmpty(message = "District cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "district cannot contain numbers")
    private String district;
}
