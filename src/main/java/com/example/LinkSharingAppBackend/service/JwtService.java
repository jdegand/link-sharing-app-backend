package com.example.LinkSharingAppBackend.service;

import java.util.Date;

import com.example.LinkSharingAppBackend.dto.UserPrincipal;

public interface JwtService {

    public String extractUsername(String token);

    public Date extractExpiration(String token);

    public Boolean validateToken(String token, UserPrincipal userPrincipal);

    public String generateToken(String email);

}
