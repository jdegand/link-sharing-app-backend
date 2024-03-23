package com.example.LinkSharingAppBackend.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LinkSharingAppBackend.entity.Link;
import com.example.LinkSharingAppBackend.service.LinkService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping()
    public ResponseEntity<List<Link>> saveLink(@Valid @RequestBody List<Link> links) { // create linkDto?
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
    public ResponseEntity<HashMap<String,String>> deleteLinkById(@PathVariable("id") int linkId) {
        linkService.deleteLinkById(linkId);
        String res = "Link " + linkId + " deleted";
        HashMap<String, String> map = new HashMap<>();
        map.put("message", res);
        return ResponseEntity.status(200).body(map);
    }
}