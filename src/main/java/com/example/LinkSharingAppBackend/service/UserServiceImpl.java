package com.example.LinkSharingAppBackend.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LinkSharingAppBackend.dto.UserInfoDto;
import com.example.LinkSharingAppBackend.entity.Role;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<UserInfoDto> addUser(UserInfo userInfo) { 
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRole(Role.USER);
        userInfo.setEnabled(true);
        UserInfo user = this.userInfoRepository.save(userInfo);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setRole(user.getRole());

        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoDto);
    }

    public ResponseEntity<UserInfo> findById(Integer id) {
        UserInfo foundUser = this.userInfoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("user", id));
        return ResponseEntity.ok(foundUser);
    }

    @Override
    public ResponseEntity<UserInfoDto> findByEmail(String email) {
        UserInfo foundUser = this.userInfoRepository.findByEmail(email).get();
        // convert to a dto -> need to remove password
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(foundUser.getId());
        userInfoDto.setEmail(foundUser.getEmail());
        userInfoDto.setUsername(foundUser.getUsername());
        userInfoDto.setProfile(foundUser.getProfile());
        userInfoDto.setRole(foundUser.getRole());
        userInfoDto.setLinks(foundUser.getLinks());

        return ResponseEntity.ok(userInfoDto);
    }

    @Override
    public ResponseEntity<UserInfoDto> findByUsernameAndId(String username, Integer id) {
        UserInfo foundUser = this.userInfoRepository.findByUsernameAndId(username, id).get();

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(foundUser.getId());
        userInfoDto.setEmail(foundUser.getEmail());
        userInfoDto.setUsername(foundUser.getUsername());
        userInfoDto.setProfile(foundUser.getProfile());
        userInfoDto.setRole(foundUser.getRole());
        userInfoDto.setLinks(foundUser.getLinks());

        return ResponseEntity.ok(userInfoDto);
    }

}
