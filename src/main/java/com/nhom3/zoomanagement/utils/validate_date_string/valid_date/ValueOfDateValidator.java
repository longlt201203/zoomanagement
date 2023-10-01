package com.nhom3.zoomanagement.utils.validate_date_string.valid_date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValueOfDateValidator implements ConstraintValidator<ValueOfDate, String> {
    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return true;
        }
        try {
            DATE_FORMAT.parse(value);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
