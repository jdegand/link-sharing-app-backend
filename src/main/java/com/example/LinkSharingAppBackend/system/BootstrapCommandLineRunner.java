package com.example.LinkSharingAppBackend.system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.LinkSharingAppBackend.entity.Role;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Component
public class BootstrapCommandLineRunner implements CommandLineRunner {

    private final UserInfoRepository userInfoRepository;

    public BootstrapCommandLineRunner(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        UserInfo adminUser = new UserInfo();
        adminUser.setEmail("admin@admin.com");
        adminUser.setEnabled(true);
        adminUser.setRole(Role.ADMIN);
        adminUser.setPassword("admin");
        adminUser.setUsername("admin");

        this.userInfoRepository.save(adminUser);
    }
    
}
