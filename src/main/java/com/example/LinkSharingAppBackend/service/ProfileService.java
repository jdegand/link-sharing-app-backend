package com.example.LinkSharingAppBackend.service;

import java.io.IOException;

import com.example.LinkSharingAppBackend.dto.ProfileDto;
import com.example.LinkSharingAppBackend.entity.Profile;

import jakarta.persistence.EntityNotFoundException;

public interface ProfileService {

    public Profile saveProfile(ProfileDto profileDto) throws IOException;

    public Profile fetchProfileById(Integer profileId) throws EntityNotFoundException;

    public Profile updateProfile(Integer profileId, ProfileDto profileDto);

}
