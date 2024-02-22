package com.example.LinkSharingAppBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LinkSharingAppBackend.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
