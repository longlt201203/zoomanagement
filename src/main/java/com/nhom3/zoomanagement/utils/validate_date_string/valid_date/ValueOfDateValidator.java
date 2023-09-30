package com.nhom3.zoomanagement.utils.validate_date_string.valid_date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValueOfDateValidator implements ConstraintValidator<ValueOfDate, String> {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DATE_FORMAT.setLenient(false);
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            DATE_FORMAT.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
