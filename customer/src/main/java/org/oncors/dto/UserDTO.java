package org.oncors.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.util.Date;

@Setter
@Getter
public class UserDTO {
    private Long userId;
//    @NotNull(message = "City cannot by null")
//    @NotEmpty(message = "City cannot be empty")
//    @Pattern(regexp = "^[A-Za-z0-9]", message = "city cannot contain numbers")
    private String city;

//    @NotNull(message = "Country cannot by null")
//    @NotEmpty(message = "Country cannot be empty")
//    @Pattern(regexp = "^[A-Za-z0-9]", message = "country cannot contain numberstttttttttt")
    private String country;

//    @NotNull(message = "District cannot by null")
//    @NotEmpty(message = "District cannot be empty")
//    @Pattern(regexp = "^[A-Za-z0-9]", message = "district cannot contain numbers")
    private String district;

//    @NotNull(message = "email cannot be null")
//    @NotEmpty(message = "email cannot be empty")
//    @Email(message = "email is not valid")
    private String email;

    @Digits(integer = 9, fraction = 0)
    private int phoneNumber;

  //  @NotNull(message = "Name cannot be null")
 //   @NotEmpty(message = "Name cannot be empty")
  //  @Pattern(regexp = "^[A-Za-z0-9]", message = "Name cannot contain numbers")
    private String name;

  //  @NotNull(message = "Surname cannot be null")
  //  @NotEmpty(message = "Surname cannot be empty")
 //   @Pattern(regexp = "^[A-Za-z0-9]", message = "Surname cannot contain numbers")
    private String surname;

   // @DateTimeFormat(pattern = "dd/MM/yyyy")
  //  @PastOrPresent
    private Date birthDate;
    private Long companyId;
}
