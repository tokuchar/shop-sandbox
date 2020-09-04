package com.oncors.api;

import com.oncors.dto.AuthRequest;
import com.oncors.service.TokenService;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import io.opentracing.propagation.TextMapExtractAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@RestController
public class AuthApi {
    @Autowired
    private TokenService tokenService;
    @Autowired
    Tracer tracer;

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
    public ResponseEntity<Boolean> validateToken(String jwtToken, @RequestHeader HttpHeaders headers) {
        SpanContext parentContext = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapExtractAdapter(headers.toSingleValueMap()));
        Span span = tracer.buildSpan("validate-token").asChildOf(parentContext).start();


        span.finish();
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

    //    @PostMapping(value = "/hello")
//    public String hello(@RequestHeader HttpHeaders headers) {
//        SpanContext parentContext = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapExtractAdapter(headers.toSingleValueMap()));
//        Span span = tracer.buildSpan("hello-auth").asChildOf(parentContext).start();
//        return "hello";
//    }
    @GetMapping(value = "/hello")
    public String hello(@RequestHeader HttpHeaders headers) {
        SpanContext parentContext = tracer.extract(Format.Builtin.HTTP_HEADERS, new TextMapExtractAdapter(headers.toSingleValueMap()));
        Span span = tracer.buildSpan("auth-hello-span").asChildOf(parentContext).start();
        span.finish();
        return "hello";
    }

}
