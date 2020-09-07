package org.oncors.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.oncors.model.entity.Company;
import org.oncors.model.entity.User;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Setter
@Getter
public class CompanyDTO {
    private Long companyId;
    @NotNull(message = "company name cannot be null")
    @NotEmpty(message = "company name cannot be empty")
    @Column(name = "name")
    private String companyName;
    private Set<UserDTO> users;

}
