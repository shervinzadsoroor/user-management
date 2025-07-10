package org.surena.usermanagement.dto.userinfo;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.dto.BaseLightDto;

@Setter
@Getter
public class UserInfoUpdateDto extends BaseLightDto {

    @Size(min = 2, max = 100, message = "firstname length must be between 2 and 100")
    private String firstName;

    @Size(min = 2, max = 150, message = "lastname length must be between 2 and 150")
    private String lastName;
}
