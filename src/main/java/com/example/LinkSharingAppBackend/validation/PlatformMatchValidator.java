package com.example.LinkSharingAppBackend.validation;

import com.example.LinkSharingAppBackend.entity.Link;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlatformMatchValidator implements ConstraintValidator<ValidatePlatformMatch, Link> {

    // Not Perfect -> possible edge cases?
    @Override
    public boolean isValid(Link value, ConstraintValidatorContext context) {
        String platform = value.getPlatform();
        String url = value.getUrl();
        return url.contains(platform + ".com");
    }

    
}
