package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Measurement extends ActivableEntity {

    @Basic
    @NotNull
    @Size(min = 1)
    private String code;

    @Basic
    @NotNull
    private Double unitValue;

    @Basic
    @NotNull
    private Boolean isBasicUnit;

}
