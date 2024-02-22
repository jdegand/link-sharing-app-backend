package com.example.LinkSharingAppBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LinkSharingAppBackend.entity.Link;
import com.example.LinkSharingAppBackend.service.LinkService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping()
    public ResponseEntity<List<Link>> saveLink(@RequestBody List<Link> links) {
        for (Link link : links) {
            linkService.saveLink(link);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(linkService.fetchLinkList());
    }

    @GetMapping()
    public List<Link> fetchLinkList() {
        return linkService.fetchLinkList();
    }

    @GetMapping("/{id}")
    public Link fetchLinkById(@PathVariable("id") int linkId)
            throws EntityNotFoundException {
        return linkService.fetchLinkById(linkId);
    }

    @DeleteMapping("/{id}")
    public String deleteLinkById(@PathVariable("id") int linkId) {
        linkService.deleteLinkById(linkId);
        String res = "Link " + linkId + " deleted";
        return res;
    }

    @PutMapping("/{id}")
    public Link updateLink(@PathVariable("id") int linkId, @RequestBody Link link) {
        return linkService.updateLink(linkId, link);
    }
}