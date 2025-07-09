package org.surena.usermanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.surena.usermanagement.validation.Edit;

@Setter
@Getter
public class BaseLightDto {

    @NotNull(groups = Edit.class)
    private Long id;

    @NotNull(groups = Edit.class)
    private Integer version;
}
