package com.example.LinkSharingAppBackend.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Profile> saveProfile(ProfileDto profileDto) throws IOException {
        /*
         * String username =
         * SecurityContextHolder.getContext().getAuthentication().getName();
         * UserInfo userInfo = this.userInfoRepository.findByEmail(username).get();
         * profile.setUserInfo(userInfo);
         * return profileRepository.save(profile);
        */
        // need to convert Profile to a Dto to save the file?

        // builder pattern? -> problem with OneToOne relationship -> Builder.default

        Profile profile = new Profile();
        profile.setEmail(profileDto.getEmail());
        profile.setFirstname(profileDto.getFirstname());
        profile.setLastname(profileDto.getLastname());
        profile.setImg(profileDto.getFile().getBytes()); // this causes a problem if file doesn't exist
        profile.setFileType(profileDto.getFileType());

        Profile savedProfile = profileRepository.save(profile);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = this.userInfoRepository.findByEmail(username).get();
        userInfo.setProfile(savedProfile);
        userInfoRepository.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
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
