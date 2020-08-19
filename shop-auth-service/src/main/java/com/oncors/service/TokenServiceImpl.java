package com.oncors.service;

import com.oncors.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Boolean validateToken(String jwtToken) {
        UserDetails userDetails = userService.loadUserByUsername(jwtUtil.extractUsername(jwtToken));
        return jwtUtil.validateToken(jwtToken, userDetails);
    }

    @Override
    public Token generateToken(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        final UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}
