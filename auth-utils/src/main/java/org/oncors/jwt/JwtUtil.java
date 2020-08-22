package org.oncors.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kong.unirest.Unirest;
import org.oncors.model.Authorities;
import org.oncors.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    private String SECRET_KEY = "secret1234";
    private final String AUTHORITIES_KEY = "AUTHORITIES";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Set<Authorities> extractAuthorities(String token) {
        Claims claims = extractAllClaims(token);
        return Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(authority -> Authorities.valueOf(authority))
                .collect(Collectors.toSet());
    }

    public UserDetails extractUserDetails(String token){
        return User.builder()
                .username(extractUsername(token))
                .authorities(extractAuthorities(token))
                .build();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token) {
        return callAuthServer(token);
    }

    public Boolean callAuthServer(String token) {
        return Boolean.parseBoolean(Unirest.post("http://localhost:8082/validate_token")
                .queryString(JwtUtilConstants.JWT_TOKEN_QUERY_PARAM, token)
                .asString().getBody());
    }

    static private class JwtUtilConstants {
        static final String JWT_TOKEN_QUERY_PARAM = "jwtToken";
    }
}
