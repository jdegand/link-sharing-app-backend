package com.example.LinkSharingAppBackend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.LinkSharingAppBackend.entity.FileData;
import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.FileDataRepository;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    private final String FOLDER_PATH = Path.of("").toAbsolutePath().toString() + "/uploads/";

    // need pass back responseEntity
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = this.userInfoRepository.findByEmail(username).get(); // orElse(null) ?

        String filePath = FOLDER_PATH + file.getOriginalFilename(); // need to worry about duplicate filenames with
                                                                    // different images

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .userInfo(userInfo)
                .build());
        

        file.transferTo(new File(filePath));

        if (fileData != null) {
            userInfo.setFileData(fileData);
            userInfoRepository.save(userInfo);
            return "file uploaded successfully : " + filePath;
        }
        // return file upload failed and throw exception?
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
