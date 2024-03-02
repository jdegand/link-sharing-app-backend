package com.example.LinkSharingAppBackend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.LinkSharingAppBackend.entity.Link;

import jakarta.persistence.EntityNotFoundException;

public interface LinkService {

    public Link saveLink(Link link);

    public List<Link> fetchLinkList();

    public Link fetchLinkById(Integer linkId) throws EntityNotFoundException;

    public ResponseEntity<Void> deleteLinkById(Integer linkId);

    public Link updateLink(Integer linkId, Link link);
}
