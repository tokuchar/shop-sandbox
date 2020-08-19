package com.oncors.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.Token;

public interface TokenService {
    Boolean validateToken(String jwtToken);
    Token generateToken(String username, String password) throws BadCredentialsException;
}