package org.surena.usermanagement.dto.userinfo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.dto.BaseLightDto;

@Setter
@Getter
public class UserInfoUpdateDto extends BaseLightDto {

    @Column(name = "FIRSTNAME", length = 100)
    private String firstName;

    @Column(name = "LASTNAME", length = 150)
    private String lastName;
}
