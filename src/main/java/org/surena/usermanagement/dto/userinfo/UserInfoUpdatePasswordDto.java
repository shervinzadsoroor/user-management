package org.surena.usermanagement.dto.userinfo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.dto.BaseLightDto;
import org.surena.usermanagement.validation.annotation.PasswordValidation;

@Setter
@Getter
public class UserInfoUpdatePasswordDto extends BaseLightDto {

    @NotNull
    private String username;

    @NotNull
    private String oldPassword;

    @NotNull
    @PasswordValidation
    private String newPassword;

    @NotNull
    private String newPasswordRepeat;
}
