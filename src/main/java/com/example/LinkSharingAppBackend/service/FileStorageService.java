package com.example.LinkSharingAppBackend.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    
    public String uploadImageToFileSystem(MultipartFile file) throws IOException;

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
