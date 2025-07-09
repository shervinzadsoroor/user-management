package org.surena.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseDto extends BaseLightDto {

    private Date createdDate;
    private Date modifiedDate;
}
