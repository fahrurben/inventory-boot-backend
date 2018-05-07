package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.VendorRepository;
import com.kyrosoft.inventory.service.VendorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VendorServiceImpl
    extends BaseServiceImpl<Vendor, VendorRepository, BaseDTO>
    implements VendorService {

    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        super(vendorRepository, Vendor.class);
        this.vendorRepository = vendorRepository;
    }

}
