package com.kyrosoft.inventory.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    private String code;

    private Long categoryId;
}
