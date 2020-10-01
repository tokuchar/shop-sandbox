package com.oncors.api;

import com.oncors.dto.AuthRequest;
import com.oncors.service.TokenService;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import jdk.jfr.StackTrace;
import lombok.extern.slf4j.Slf4j;
import org.oncors.aop.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@RestController
public class AuthApi {
    @Autowired
    private TokenService tokenService;

    @Trace
    @PostMapping(value = "/authenticate")
    public ResponseEntity<Token> authenticate(@RequestHeader HttpHeaders headers, @RequestBody AuthRequest authRequest) {
        final Token jwtToken;
        try {
            jwtToken = tokenService.generateToken(authRequest.getUsername(), authRequest.getPassword());
        } catch (BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "incorrect username or password", exception);
        }
        return ResponseEntity.ok(jwtToken);
    }

    @Trace
    @PostMapping(value = "/validate_token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader HttpHeaders headers, String jwtToken) {
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

}
