package com.oncors.service;

import com.oncors.model.Authorities;
import com.oncors.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDetailsService {
    //in future this will be our user repository (with database)
    private static Map<String, UserDetails> userRepository = Stream.of(
            new AbstractMap.SimpleEntry<>("JanKowalski", User.builder()
                    .username("JanKowalski")
                    .password("$2a$04$lSTpwQEoKkDbcrYk29uLS.BgnE/yydfzDOsAwsMN0inxtqINPN1mq")
                    .authorities(Stream.of(Authorities.USER, Authorities.ADMIN).collect(Collectors.toSet()))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true).build())
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.get(username);
    }
}