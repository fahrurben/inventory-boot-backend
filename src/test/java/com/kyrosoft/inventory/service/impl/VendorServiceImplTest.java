package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VendorServiceImplTest {

    @Autowired
    VendorService vendorService;

    @Before
    public void before() {
        ServiceContext.setCurrentUser("john");
    }

    @Test
    public void testCreateVendor() throws ServiceException {
        Vendor vendor = new Vendor();
        vendor.setName("test");
        vendor.setIsActive(true);

        vendorService.create(vendor);
    }

}
