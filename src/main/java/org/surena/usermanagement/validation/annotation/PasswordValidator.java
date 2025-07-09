package org.surena.usermanagement.validation.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.surena.usermanagement.exception.InvalidPasswordException;

import java.util.List;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    private static final List<Character> characters = List.of(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')',
            '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?',
            '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~');

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext constraintValidatorContext) {
        if (pass != null) {
            ValidationDetail validate = validate(pass);
            if (validate.isValid()) {
                return true;
            }
            InvalidPasswordException exception = new InvalidPasswordException(validate.getErrorMessage());
            throw new RuntimeException(exception);
        }
        return true;
    }

    private ValidationDetail validate(String pass) {
        ValidationDetail detail = new ValidationDetail();
        String errorMessage = null;
        if (pass.length() < 8)
            errorMessage = "رمز عبور میبایست حداقل 8 کاراکتر باشد.";
        else if (pass.length() > 40)
            errorMessage = "رمز عبور میبایست حداکثر 40 کاراکتر باشد.";
        else if (pass.contains(" "))
            errorMessage = "رمز عبور میبایست پیوسته و بدون فضای خالی باشد.";
        else if (!pass.matches(".*[a-z].*"))
            errorMessage = "رمز عبور میبایست دارای حروف کوچک از a تا z باشد.";
        else if (!pass.matches(".*[A-Z].*"))
            errorMessage = "رمز عبور میبایست دارای حروف بزرگ از A تا Z باشد.";
        else if (!pass.matches(".*\\d.*"))
            errorMessage = "رمز عبور میبایست دارای یک عدد باشد.";
        else if (characters.stream().noneMatch(
                character -> pass.contains(String.valueOf(character)))) {
            errorMessage = "رمز عبور میبایست دارای یک سمبل (@،!،%و..) باشد.";
        } else {
            detail.setValid(true);
        }
        if (!detail.isValid()) {
            detail.setErrorMessage(errorMessage);
        }
        return detail;
    }

}
