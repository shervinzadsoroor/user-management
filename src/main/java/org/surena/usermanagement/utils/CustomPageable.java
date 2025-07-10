package org.surena.usermanagement.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.surena.usermanagement.dto.BaseDto;

@Setter
@Getter
public class CustomPageable<D extends BaseDto> {

    private D searchDto;
    private int page;
    private int size;
    private String sortBy = "ID";
    private Sort.Direction direction = Sort.Direction.DESC;
}
