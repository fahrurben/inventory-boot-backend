package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends ActivableEntity {

    @Basic
    @NotNull
    @Size(min = 1)
    private String code;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="categoryId")
    private ProductCategory category;

    @Basic
    private String description;

    @Basic
    private String remarks;

    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JoinColumn(name="basicUnitId")
    private Measurement basicUnit;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="salesUnitId")
    private Measurement salesUnit;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="purchaseUnitId")
    private Measurement purchaseUnit;
}
