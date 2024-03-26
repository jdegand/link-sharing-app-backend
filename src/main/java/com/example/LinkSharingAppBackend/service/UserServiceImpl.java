package com.example.LinkSharingAppBackend.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LinkSharingAppBackend.entity.Role;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRole(Role.USER);
        userInfo.setEnabled(true);
        return this.userInfoRepository.save(userInfo);
    }

    public UserInfo findById(Integer id) {
        return this.userInfoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
    }

    @Override
    public UserInfo findByEmail(String email) {
        return this.userInfoRepository.findByEmail(email).get();
    }

    @Override
    public UserInfo findByUsernameAndId(String username, Integer id) {
        return this.userInfoRepository.findByUsernameAndId(username, id).get();
    }

}
