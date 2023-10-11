package com.nhom3.zoomanagement.utils.validate_date_string.past_or_present;

import com.nhom3.zoomanagement.utils.validate_date_string.valid_date.ValueOfDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PACKAGE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfDateValidator.class)
public @interface PastOrPresentDate {
    String message() default "Invalid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
