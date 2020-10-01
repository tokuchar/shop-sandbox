package org.oncors.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Setter
@Getter
public class PersonalDataDTO {
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
}
