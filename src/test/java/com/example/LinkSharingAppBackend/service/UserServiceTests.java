package com.example.LinkSharingAppBackend.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.LinkSharingAppBackend.entity.Role;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserInfoRepository userInfoRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void UserInfoService_CreateUserInfo_ReturnsUserInfoDto() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("aaa@mail.com");
        userInfo.setUsername("aaa");
        userInfo.setPassword("password");
        userInfo.setEnabled(true);
        userInfo.setRole(Role.USER);

        when(userInfoRepository.save(Mockito.any(UserInfo.class))).thenReturn(userInfo);

        UserInfo savedUserInfo = userService.addUser(userInfo);

        Assertions.assertThat(savedUserInfo).isNotNull();
        Assertions.assertThat(savedUserInfo.getRole()).isEqualTo(Role.USER);
    }
    
}
