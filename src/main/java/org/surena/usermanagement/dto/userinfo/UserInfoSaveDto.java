package org.surena.usermanagement.dto.userinfo;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
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

    @Column(name = "FIRSTNAME", length = 100)
    private String firstName;

    @Column(name = "LASTNAME", length = 150)
    private String lastName;
}
