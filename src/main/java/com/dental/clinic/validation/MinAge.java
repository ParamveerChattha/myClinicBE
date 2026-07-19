package com.dental.clinic.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinAgeValidator.class)
@Documented
public @interface MinAge {
    String message() default "patient must be atleast {value} years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
    int value();
}
