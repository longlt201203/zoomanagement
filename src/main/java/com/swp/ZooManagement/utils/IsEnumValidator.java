package com.swp.ZooManagement.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class IsEnumValidator implements ConstraintValidator<IsEnum, String> {
    private List<String> availableValues;

    @Override
    public void initialize(IsEnum constraintAnnotation) {
        availableValues = new ArrayList<>();
        for (Enum<?> enumConst : constraintAnnotation.enumClass().getEnumConstants()) {
            availableValues.add(enumConst.name());
        }
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String e, ConstraintValidatorContext constraintValidatorContext) {
        if (e != null) {
            return availableValues.contains(e);
        }
        return false;
    }
}
