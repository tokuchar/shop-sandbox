package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @NotNull(message = "City cannot by null")
    @NotEmpty(message = "City cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "city cannot contain numbers")
    private String city;

    @Pattern(regexp = "^[A-Za-z0-9]", message = "country cannot contain numbers")
    private String country;

    @Pattern(regexp = "^[A-Za-z0-9]", message = "district cannot contain numbers")
    private String district;

    @OneToOne(mappedBy = "address")
    private User user;
}