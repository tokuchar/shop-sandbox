package com.oncors.api;

import com.oncors.dto.AuthRequest;
import com.oncors.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthApi {
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Token> authenticate(@RequestBody AuthRequest authRequest) {
        final Token jwtToken;
        try {
            jwtToken = tokenService.generateToken(authRequest.getUsername(), authRequest.getPassword());
        } catch (BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "incorrect username or password", exception);
        }
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping(value = "/validate_token")
    public ResponseEntity<Boolean> validateToken(String jwtToken) {
        Boolean isTokenValid = false;
        try {
            isTokenValid = tokenService.validateToken(jwtToken);
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
        return ResponseEntity.ok(isTokenValid);
    }

    @GetMapping(value = "/hello")
    public String helloWorld() {
        return "hello world!";
    }

    @GetMapping(value = "/helloAdmin")
    public String helloAdmin() {
        return "hello admin!";
    }
}
