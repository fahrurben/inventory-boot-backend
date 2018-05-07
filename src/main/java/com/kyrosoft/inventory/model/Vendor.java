package com.kyrosoft.inventory.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vendor extends ActivableEntity {

    @Basic
    private String contactName;

    @Basic
    private String contactPhone;

    @Basic
    private String contactFax;

    @Basic
    private String contactEmail;

    @Basic
    private String contactWebsite;

    @Basic
    private String addressStreet;

    @Basic
    private String addressCity;

    @Basic
    private String addressProvince;

    @Basic
    private String addressPostalCode;

    @Basic
    private String addressRemarks;

    @Basic
    private String remarks;

}
