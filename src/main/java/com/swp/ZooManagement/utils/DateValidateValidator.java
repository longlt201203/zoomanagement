package com.swp.ZooManagement.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.time.temporal.Temporal;

public class DateValidateValidator implements ConstraintValidator<DateValidate, Instant> {
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
    public boolean isValid(Instant instant, ConstraintValidatorContext constraintValidatorContext) {
        if (!allowPast && instant.isBefore(Instant.now())) {
            return false;
        }

        return false;
    }
}
