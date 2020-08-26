package org.oncors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Valid
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Valid
    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @Valid
    @OneToOne
    @JoinColumn(name = "personal_data_id")
    private PersonalData personalData;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;
}
