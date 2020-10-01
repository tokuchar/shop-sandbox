package org.oncors.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private PersonalDataDTO personalData;
    private ContactDTO contact;
    private AddressDTO address;
    private Long companyId;
}
