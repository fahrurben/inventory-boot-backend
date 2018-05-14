package com.kyrosoft.inventory.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base DTO, base class for data tranfer object.
 * Using in search function
 *
 * @author fahrur
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseDTO {

    private Integer page;

    private Integer size;

    private String sortBy;

    private SortType sortType;

    private String name;

    private Boolean isActive;

}
