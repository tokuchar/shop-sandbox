package com.oncors.jwt;

import com.oncors.dto.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtil {
    @Value("${token.secretKey}")
    private String secretKey;
    @Value("${token.expirationTime}'")
    private String expirationTime;
    private final String AUTHORITIES_KEY = "AUTHORITIES";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Token generateToken(UserDetails userDetails) {
        Date tokenCreationDate = new Date(System.currentTimeMillis());
        Map<String, Object> claims = //new HashMap<>();
                createClaims(userDetails);

        String tokenString = createToken(claims, userDetails.getUsername(), tokenCreationDate);

        return JwtToken.builder()
                .key(tokenString)
                .keyCreationTime(tokenCreationDate.getTime()).build();
    }

    private String createToken(Map<String, Object> claims, String username, Date tokenCreationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(tokenCreationDate)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Map<String, Object> createClaims(UserDetails userDetails) {
        String authorities = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(","));
        return new HashMap<String, Object>() {
            {
                put(AUTHORITIES_KEY, authorities);
            }
        };
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
