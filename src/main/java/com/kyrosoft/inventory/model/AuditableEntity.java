package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class AuditableEntity extends LookupEntity {

    @Basic
    @NotNull
    @Size(min=1)
    private String createdBy;

    @Basic
    @NotNull
    private Date createdDate;

    @Basic
    @NotNull
    @Size(min=1)
    private String updatedBy;

    @Basic
    @NotNull
    private Date updatedDate;
}
