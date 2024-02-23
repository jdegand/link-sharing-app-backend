package com.example.LinkSharingAppBackend.service;

import org.springframework.http.ResponseEntity;

import com.example.LinkSharingAppBackend.dto.UserInfoDto;
import com.example.LinkSharingAppBackend.entity.UserInfo;

public interface UserService {
    
    public ResponseEntity<UserInfoDto> addUser(UserInfo userInfo);

    public ResponseEntity<UserInfo> findById(Integer id);

    public ResponseEntity<UserInfoDto> findByEmail(String email);
}
