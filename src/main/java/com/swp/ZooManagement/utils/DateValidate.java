package com.swp.ZooManagement.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidateValidator.class)
@Documented
public @interface DateValidate {
    String message() default "Invalid Date value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean allowPast() default true;
    int minYear() default 1800;
    int maxYear() default 2100;
}
