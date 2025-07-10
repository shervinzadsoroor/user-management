package org.surena.usermanagement.dto.userinfo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.validation.annotation.PasswordValidation;
import org.surena.usermanagement.validation.annotation.UsernameValidation;

@Setter
@Getter
public class UserInfoSaveDto {

    @NotNull
    @UsernameValidation
    private String username;

    @NotNull
    @PasswordValidation
    private String password;

    @Size(min = 2, max = 100, message = "firstname length must be between 2 and 100")
    private String firstName;

    @Size(min = 2, max = 150, message = "lastname length must be between 2 and 150")
    private String lastName;
}
