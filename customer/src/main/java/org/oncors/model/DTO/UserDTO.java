package org.oncors.model.DTO;

import lombok.Getter;
import lombok.Setter;
import org.oncors.model.entity.Address;
import org.oncors.model.entity.Contact;
import org.oncors.model.entity.PersonalData;

import javax.validation.Valid;

@Setter
@Getter
public class UserDTO {
    private Long userId;
    @Valid
    private AddressDTO address;
    @Valid
    private ContactDTO contact;
    @Valid
    private PersonalDataDTO personalData;
    private Long companyId;
}
