package org.oncors.dto;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CompanyDTO {
    private Long companyId;
    @NotNull(message = "company name cannot be null")
    @NotEmpty(message = "company name cannot be empty")
    @Column(name = "name")
    private String companyName;
}
