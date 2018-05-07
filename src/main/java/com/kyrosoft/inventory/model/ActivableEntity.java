package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class ActivableEntity extends AuditableEntity {

    @Basic
    @NotNull
    private Boolean isActive;
}
