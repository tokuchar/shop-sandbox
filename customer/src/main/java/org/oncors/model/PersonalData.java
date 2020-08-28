package org.oncors.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Getter
@Setter
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long personalDataId;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "Name cannot contain numbers")
    private String name;
    @NotNull(message = "Surname cannot be null")
    @NotEmpty(message = "Surname cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9]", message = "Surname cannot contain numbers")
    private String surname;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent
    private Date birthDate;

    @OneToOne(mappedBy = "personalData")
    private User user;

}
