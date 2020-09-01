package org.oncors.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    USER,
    ADMIN,
    REGISTRATION_MICROSERVICE;

    @Override
    public String getAuthority() {
        return name();
    }
}
