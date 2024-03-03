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

import jakarta.persistence.EntityNotFoundException;

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

    @Override
    public Profile fetchProfileById(Integer profileId) throws EntityNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchProfileById'");
    }

    @Override
    public Profile updateProfile(Integer profileId, ProfileDto profileDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
    }

}
