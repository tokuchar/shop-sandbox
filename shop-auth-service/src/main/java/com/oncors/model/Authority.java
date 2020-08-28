package com.oncors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    ShopAuthority authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }
}
