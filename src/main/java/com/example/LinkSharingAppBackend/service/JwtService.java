package com.example.LinkSharingAppBackend.service;

import java.util.Date;
import java.util.function.Function;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;

import io.jsonwebtoken.Claims;

public interface JwtService {

    public String extractUsername(String token);

    public Date extractExpiration(String token);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    public Boolean validateToken(String token, UserPrincipal userPrincipal);

    public String generateToken(String name);

}
