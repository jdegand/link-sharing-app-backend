package com.example.LinkSharingAppBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.LinkSharingAppBackend.entity.Link;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.LinkRepository;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public Link saveLink(Link link) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = this.userInfoRepository.findByEmail(username).get();
        link.setUserInfo(userInfo);
        return linkRepository.save(link);
    }

    @Override
    public List<Link> fetchLinkList() {
        return linkRepository.findAll();
    }

    @Override
    public Link fetchLinkById(Integer linkId) throws EntityNotFoundException {
     Optional<Link> link = linkRepository.findById(linkId);

        if (!link.isPresent()) {
            throw new EntityNotFoundException("Link Not Available");
        }

        return link.get();
    }

    @Override
    public void deleteLinkById(Integer linkId) { // Use Response object and return that
        linkRepository.deleteById(linkId);
    }

    @Override
    public Link updateLink(Integer linkId, Link link) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLink'");
    }
    
}
