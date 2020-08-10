package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotNull(message = "City cannot by null")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "city cannot contain numbers")
    @Column
    private String city;

    @NotNull(message = "street cannot by null")
    @Column
    private String street;

    @Pattern(regexp = "^\\d{2}-\\d{3}", message = "valid zip code format is xx-xxx")
    @Column
    private String zipCode;

    @NotNull(message = "house number cannot by null")
    @Column
    private String houseNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customer_address_mapping", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Collection<Customer> customer = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "company_address_mapping", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Collection<Company> company = new ArrayList<>();
}