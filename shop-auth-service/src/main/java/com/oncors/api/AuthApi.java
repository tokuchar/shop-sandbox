package com.oncors.api;

import com.oncors.dto.AuthRequest;
import com.oncors.model.User;
import com.oncors.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
@EnableSwagger2
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
        log.info("\nvalidate token: " + jwtToken + "\nfor sessionId: " +
                RequestContextHolder.currentRequestAttributes().getSessionId());

        Boolean isTokenValid;
        try {
            isTokenValid = tokenService.validateToken(jwtToken);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        }
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping(value = "/create_account")
    public ResponseEntity<Void> createAccount(@RequestBody User createAccount){
        throw new NotImplementedException();
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
