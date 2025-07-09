package org.surena.usermanagement.validation.annotation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
final class ValidationDetail {

    private boolean isValid;
    private String errorMessage;
}
