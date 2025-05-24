package org.example.notificationapp.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.notificationapp.validation.UserDataValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserDataValidation.class)
public @interface ValidUserData {

    String message() default "Invalid user data (username or user email)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
