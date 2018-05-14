package com.kyrosoft.inventory.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeasurementDTO extends BaseDTO {

    private String code;

    private Boolean isBasicUnit;

}
