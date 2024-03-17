package com.example.LinkSharingAppBackend.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LinkSharingAppBackend.entity.RefreshToken;
import com.example.LinkSharingAppBackend.exception.InvalidTokenException;
import com.example.LinkSharingAppBackend.repository.RefreshTokenRepository;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Service
public class RefreshTokenServiceImpl {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    public RefreshToken createRefreshToken(String name) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(userInfoRepository.findByEmail(name).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))// 10 minutes
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new InvalidTokenException(token.getToken() + " Refresh token is expired. Please sign in again.");
        }
        return token;
    }

}
