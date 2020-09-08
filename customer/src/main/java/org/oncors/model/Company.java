package org.oncors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @NotNull(message = "company name cannot be null")
    @NotEmpty(message = "company name cannot be empty")
    @Column(name = "name")
    private String companyName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private Set<User> users;
}
