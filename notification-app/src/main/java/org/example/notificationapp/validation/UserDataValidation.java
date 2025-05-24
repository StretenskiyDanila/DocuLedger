package org.example.notificationapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.notificationapp.annotations.ValidUserData;
import org.example.notificationapp.dto.NotificationDto;
import org.springframework.beans.BeanWrapperImpl;

public class UserDataValidation implements ConstraintValidator<ValidUserData, Object> {

    @Override
    public void initialize(ValidUserData constraintAnnotation) {}

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        NotificationDto dto = (NotificationDto) value;
        return !(isNullOrEmpty(dto.userMail()) && isNullOrEmpty(dto.userName()));
    }

    private boolean isNullOrEmpty(String data) {
        return data == null || data.isBlank();
    }

}
