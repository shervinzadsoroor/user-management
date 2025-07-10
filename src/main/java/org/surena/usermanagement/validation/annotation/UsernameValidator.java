package org.surena.usermanagement.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.surena.usermanagement.exception.InvalidUsernameException;

public class UsernameValidator implements ConstraintValidator<UsernameValidation, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        ValidationDetail validate = validate(username);
        if (validate.isValid()) {
            return true;
        }
        throw new InvalidUsernameException(validate.getErrorMessage());
    }

    private ValidationDetail validate(String username) {
        ValidationDetail detail = new ValidationDetail();
        String errorMessage = null;
        if (username.isBlank())
            errorMessage = "نام کاربری خالی است.";
        else if (username.contains(" "))
            errorMessage = "نام کاربری میبایست پیوسته و بدون فاصله باشد.";
        else if (username.length() < 5 || username.length() > 50)
            errorMessage = "نام کاربری باید بین ۵ تا ۵۰ کاراکتر باشد.";
        else {
            detail.setValid(true);
        }
        if (!detail.isValid()) {
            detail.setErrorMessage(errorMessage);
        }
        return detail;
    }
}
