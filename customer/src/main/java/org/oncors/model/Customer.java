package org.oncors.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String login;
    @NotNull(message = "name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "name cannot contain numbers")
    @NotEmpty(message = "name name cannot be empty")
    @Column
    private String name;

    @NotNull(message = "surname cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "surname cannot contain numbers")
    @NotEmpty(message = "surname name cannot be empty")
    @Column
    private String surname;

    @NotNull(message = "birth date cannot be null")
    @Past(message = "birth date cannot be today")
    @NotEmpty(message = "birth date name cannot be empty")
    @Column
    private LocalDate birthDate;

    @Valid
    @NotNull(message = "address cannot be null")
    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    @Valid
    @NotNull(message = "Contact cannot be null")
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="contact_id")
    private  Contact contact;

}
