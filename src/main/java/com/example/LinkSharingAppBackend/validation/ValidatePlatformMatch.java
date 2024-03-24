package com.example.LinkSharingAppBackend.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PlatformMatchValidator.class)
public @interface ValidatePlatformMatch {

    public String message() default "Platform & Url must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
