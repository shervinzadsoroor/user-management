package org.surena.usermanagement.dto.userinfo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.dto.BaseLightDto;

@Setter
@Getter
public class UserInfoDeleteDto extends BaseLightDto {

    @NotNull
    private String username;
}
