package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String login;
    @NotNull(message = "company name cannot be null")
    @NotEmpty(message = "company name cannot be empty")
    @Column
    private String companyName;

    @NotNull(message = "nip name cannot be empty")
    @Digits(integer = 10, fraction = 0, message = "tax number should contain only 10 digits")
    @Column
    private String taxNumber;

    @Valid
    @NotNull(message = "address cannot be null")
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    @Valid
    @NotNull(message = "Contact cannot be null")
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private Contact contact;
}
