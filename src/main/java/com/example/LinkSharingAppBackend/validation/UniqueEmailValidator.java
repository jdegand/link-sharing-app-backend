package com.example.LinkSharingAppBackend.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.LinkSharingAppBackend.entity.UserInfo;
import com.example.LinkSharingAppBackend.repository.UserInfoRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<ValidateUniqueEmailType, String> {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<UserInfo> foundUserInfo = userInfoRepository.findByEmail(value);
       if(foundUserInfo.isPresent()) {
        return false;
       }
       return true;
    }

}
