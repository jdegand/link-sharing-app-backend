package com.example.LinkSharingAppBackend.service;

import java.io.IOException;

import com.example.LinkSharingAppBackend.dto.ProfileDto;
import com.example.LinkSharingAppBackend.entity.Profile;

public interface ProfileService {

    public Profile saveProfile(ProfileDto profileDto) throws IOException;
}
