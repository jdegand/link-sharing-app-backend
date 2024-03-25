package com.example.LinkSharingAppBackend.validation;

import com.example.LinkSharingAppBackend.entity.Link;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PlatformMatchValidator implements ConstraintValidator<ValidatePlatformMatch, Link> {

    /**
     * Not Perfect -> possible edge cases?
     * Could add toLowerCase() here and in the validator (value.toLowerCase())
     * String platform = value.getPlatform().toLowerCase();
     * String url = value.getUrl().toLowerCase();
     * Would need to add an extra toLowerCase in the frontend
     * as the platform needs to be lowercased to match the primeng
     * icon keyword
     * 
     * @param value
     * @param context
     * @return boolean
     */
    @Override
    public boolean isValid(Link value, ConstraintValidatorContext context) {
        String platform = value.getPlatform();
        String url = value.getUrl();
        return url.contains(platform + ".com");
    }

}
