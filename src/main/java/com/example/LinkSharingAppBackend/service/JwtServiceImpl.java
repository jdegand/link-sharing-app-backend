package com.example.LinkSharingAppBackend.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtServiceImpl implements JwtService {

    // openssl rand -hex 32
    private static final String SECRET = "0f5a70d6c7a815321269d309f42743d433348ca94101bbd27e29c46c01bd9802";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        Boolean v = extractExpiration(token).before(new Date());
        log.info("isTokenExpired: " + v.toString());
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserPrincipal userPrincipal) {
        final String username = extractUsername(token);
        log.info("validated token's username is: ", extractUsername(token));
        return (username.equals(userPrincipal.getUsername()) &&
                !isTokenExpired(token));
    }

    public String generateToken(String name) {
        // name is empty
        log.info("generateToken's name is: ", name);
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, name);
    }

    private String createToken(Map<String, Object> claims, String name) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(name)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 1)) // 1 mins
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
/*
 * public Boolean validateToken(String token) {
 * try {
 * Jws<Claims> claims = Jwts.parserBuilder()
 * .setSigningKey(getSignKey())
 * .build()
 * .parseClaimsJws(token);
 * 
 * return !claims.getBody().getExpiration().before(new Date());
 * } catch (Exception e) {
 * throw new JwtExpiredException("JWT token has expired");
 * }
 * }
 * 
 * public static class JwtExpiredException extends RuntimeException {
 * public JwtExpiredException(String message) {
 * super(message);
 * }
 * }
 */
