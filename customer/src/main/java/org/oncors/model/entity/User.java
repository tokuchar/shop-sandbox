package org.oncors.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Company company;
}
