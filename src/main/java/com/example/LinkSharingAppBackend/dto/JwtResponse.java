package com.example.LinkSharingAppBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    // private Object validUser;
    // email -> validUser.validUser.username
    // role
    // userId
    // private Role role;
    // private Integer userId;
}
