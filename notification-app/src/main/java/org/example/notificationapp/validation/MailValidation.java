package org.example.notificationapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.notificationapp.annotations.ValidMail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidation implements ConstraintValidator<ValidMail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidMail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return validateEmail(email);
    }

    private boolean validateEmail(String email) {
        if (email == null) return false;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
