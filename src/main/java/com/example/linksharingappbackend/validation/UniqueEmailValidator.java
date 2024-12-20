package com.example.linksharingappbackend.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.linksharingappbackend.entity.UserInfo;
import com.example.linksharingappbackend.repository.UserInfoRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// either approach works if you add 
// spring.jpa.properties.javax.persistence.validation.mode=none
// to your application.properties file

public class UniqueEmailValidator implements ConstraintValidator<ValidateUniqueEmailType, String> {

    // without spring.jpa.properties.javax.persistence.validation.mode=none
    // userInfoRepository will be null in this validator

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<UserInfo> foundUserInfo = this.userInfoRepository.findByEmail(value);
        if (!foundUserInfo.isPresent()) {
            return true;
        }
        return false;
    }

}

/*
 * @Configuration
 * public class UniqueEmailValidator implements
 * ConstraintValidator<ValidateUniqueEmailType, String> {
 * 
 * private static final UniqueEmailValidator holder = new
 * UniqueEmailValidator();
 * 
 * @Bean
 * public static UniqueEmailValidator bean(UserInfoRepository repository) {
 * holder.repository = repository;
 * return holder;
 * }
 * 
 * private UserInfoRepository repository;
 * 
 * @Override
 * public boolean isValid(String value, ConstraintValidatorContext context) {
 * var data = holder.repository.findByEmail(value);
 * 
 * if(data.isPresent()){
 * return false;
 * }
 * return true;
 * }
 * }
 */