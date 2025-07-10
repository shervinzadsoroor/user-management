package org.surena.usermanagement.dto.userinfo;

import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.dto.BaseDto;

@Setter
@Getter
public class UserInfoDto extends BaseDto {

    private String username;
    private String firstName;
    private String lastName;
}
