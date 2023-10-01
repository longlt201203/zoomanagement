package com.nhom3.zoomanagement.utils.validate_date_string.past_or_present;

import com.nhom3.zoomanagement.utils.validate_date_string.future_or_present.FutureOrPresentDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PastOrPresentValidator implements ConstraintValidator<FutureOrPresentDate, String> {
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DATE_FORMAT.setLenient(false);
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            Date dateInput = DATE_FORMAT.parse(value);
            Date currentDate = new Date();
            if (dateInput.after(currentDate)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}