package com.example.LinkSharingAppBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String email;

    // OneToOne with UserInfo
    // @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
    // "fileData")
    // private FileData fileData;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    private String fileType;

    @OneToOne(mappedBy = "profile")
    @JsonIgnore
    private UserInfo userInfo;
}
