package com.example.LinkSharingAppBackend.service;

import com.example.LinkSharingAppBackend.entity.UserInfo;

public interface UserService {
    
    public UserInfo addUser(UserInfo userInfo);

    public UserInfo findById(Integer id);

    public UserInfo findByEmail(String email);

    public UserInfo findByUsernameAndId(String username, Integer id);
}
