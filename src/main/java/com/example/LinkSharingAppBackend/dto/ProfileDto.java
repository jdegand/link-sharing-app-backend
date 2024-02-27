package com.example.LinkSharingAppBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {
    private String firstname;
    private String lastname;
    private String email;
    //private MultipartFile file;
    private String file;
    private String fileType;
}
