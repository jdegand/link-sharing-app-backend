package com.example.LinkSharingAppBackend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.LinkSharingAppBackend.dto.ProfileDto;
import com.example.LinkSharingAppBackend.entity.Profile;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.ProfileRepository;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Profile saveProfile(ProfileDto profileDto) throws IOException {

        Profile profile = new Profile();
        profile.setEmail(profileDto.getEmail());
        profile.setFirstname(profileDto.getFirstname());
        profile.setLastname(profileDto.getLastname());
        profile.setImg(profileDto.getFile().getBytes());
        profile.setFileType(profileDto.getFileType());

        Profile savedProfile = profileRepository.save(profile);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = this.userInfoRepository.findByEmail(username).get();
        userInfo.setProfile(savedProfile);
        userInfoRepository.save(userInfo);
        return savedProfile;
    }

    /* 
    @Override
    public Profile saveProfile(ProfileDto profileDto) throws IOException {
        Profile profile = new Profile();
        profile.setEmail(profileDto.getEmail());
        profile.setFirstname(profileDto.getFirstname());
        profile.setLastname(profileDto.getLastname());
        profile.setImg(profileDto.getFile().getBytes());
        profile.setFileType(profileDto.getFileType());

        Profile savedProfile = profileRepository.save(profile);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (username != null) {
            UserInfo userInfo = this.userInfoRepository.findByEmail(username); // need to remove optional
            if (userInfo != null) {
                userInfo.setProfile(savedProfile);
                userInfoRepository.save(userInfo);
            } else {
                // Handle case where userInfo is null
            }
        } else {
            // Handle case where username is null
        }

        return savedProfile;
    }
    */

}
