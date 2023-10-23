package com.swp.ZooManagement.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;

public class DateValidateValidator implements ConstraintValidator<DateValidate, String> {
    private boolean allowPast;
    private int minYear;
    private int maxYear;

    @Override
    public void initialize(DateValidate constraintAnnotation) {
        allowPast = constraintAnnotation.allowPast();
        maxYear = constraintAnnotation.maxYear();
        minYear = constraintAnnotation.minYear();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Instant instant = Instant.parse(s);
            if (!allowPast && instant.isBefore(Instant.now())) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
