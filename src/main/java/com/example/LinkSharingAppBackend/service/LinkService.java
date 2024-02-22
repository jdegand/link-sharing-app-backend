package com.example.LinkSharingAppBackend.service;

import java.util.List;

import com.example.LinkSharingAppBackend.entity.Link;

import jakarta.persistence.EntityNotFoundException;

public interface LinkService {

    public Link saveLink(Link link);
    
    //public List<Link> saveLinks(List<Link> links);

    public List<Link> fetchLinkList();

    public Link fetchLinkById(Integer linkId) throws EntityNotFoundException;

    public void deleteLinkById(Integer linkId);

    public Link updateLink(Integer linkId, Link link);
}
