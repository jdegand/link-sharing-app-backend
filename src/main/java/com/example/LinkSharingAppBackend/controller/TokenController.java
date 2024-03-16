package com.example.LinkSharingAppBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LinkSharingAppBackend.dto.AuthRequest;
import com.example.LinkSharingAppBackend.dto.JwtResponse;
import com.example.LinkSharingAppBackend.dto.RefreshTokenRequest;
import com.example.LinkSharingAppBackend.entity.RefreshToken;
import com.example.LinkSharingAppBackend.exception.InvalidTokenException;
import com.example.LinkSharingAppBackend.service.JwtService;
import com.example.LinkSharingAppBackend.service.RefreshTokenServiceImpl;

@RestController
@RequestMapping("/auth")
public class TokenController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenService;

    @PostMapping("/authenticate")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication validUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (validUser.isAuthenticated()) {

            String email = authRequest.getEmail();

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(email);
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(email))
                    .refreshToken(refreshToken.getToken()).build();

        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    // have to make sure this method throws an exception
    // because route can't be blocked inside security config
    @PostMapping("/refresh")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getEmail());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new InvalidTokenException(
                        "Refresh token invalid"));
    }
}
