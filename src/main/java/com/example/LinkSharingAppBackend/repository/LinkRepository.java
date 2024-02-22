package com.example.LinkSharingAppBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LinkSharingAppBackend.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Integer> {

}