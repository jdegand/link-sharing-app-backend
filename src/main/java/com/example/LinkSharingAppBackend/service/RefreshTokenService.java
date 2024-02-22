package com.example.LinkSharingAppBackend.service;

import java.util.Optional;

import com.example.LinkSharingAppBackend.entity.RefreshToken;

public interface RefreshTokenService {
    
    public RefreshToken createRefreshToken(String name);

    public Optional<RefreshToken> findByToken(String token);

    public RefreshToken verifyExpiration(RefreshToken token);
}
