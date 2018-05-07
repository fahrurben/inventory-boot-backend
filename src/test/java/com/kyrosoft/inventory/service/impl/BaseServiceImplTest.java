package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseServiceImplTest {

    protected final String stringTest = "test";

    protected final Long idTest = 1L;

    protected final Long longTest = 1L;

    protected final Double doubleTest = 1.0;

    protected final Boolean boolTest = true;

    protected final Date dateTest = new Date(115,0,1);

    protected final String stringUpdated = "updated";

    protected final Long longUpdated = 2L;

    protected final Double doubleUpdated = 2.0;

    protected final Boolean boolUpdated = false;

    protected final Date dateUpdated = new Date(114,0,1);

    protected final Long IdTest = 1L;

    protected final Integer seedNumber = 20;

    protected List<Vendor> vendors = new ArrayList<>();

    @Autowired
    VendorRepository vendorRepository;

    protected void seedFactory() {
        this.vendors = new ArrayList<>();

        vendorSeed();
    }

    protected void vendorSeed() {
        if(this.vendors.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Vendor vendor = new Vendor();
                vendor.setName(stringTest + i);
                vendor.setIsActive(boolTest);
                vendor.setCreatedBy(stringTest);
                vendor.setCreatedDate(dateTest);
                vendor.setUpdatedBy(stringTest);
                vendor.setUpdatedDate(dateTest);
                vendorRepository.save(vendor);

                this.vendors.add(vendor);
            }
        }
    }
}
