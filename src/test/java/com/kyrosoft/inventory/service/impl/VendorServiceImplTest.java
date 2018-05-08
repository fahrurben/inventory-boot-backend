package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.service.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class VendorServiceImplTest extends BaseServiceImplTest {

    @Autowired
    VendorService vendorService;

    @Before
    public void before() {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
    }

    @Test
    public void createVendorAndRetrieve_OK() throws ServiceException {
        Vendor vendor = new Vendor();
        vendor.setName(stringTest);
        vendor.setIsActive(boolTest);

        vendorService.create(vendor);

        Vendor created = vendorService.get(vendor.getId());
        assertEquals(vendor.getName(), created.getName());
        assertEquals(vendor.getIsActive(), created.getIsActive());
    }

    @Test
    public void updateVendorAndRetrieve_OK() throws ServiceException {
        Vendor vendor = vendors.get(0);
        vendor.setName(stringUpdated);
        vendor.setIsActive(boolUpdated);

        vendorService.update(vendor);
        Vendor updated = vendorService.get(vendor.getId());
        assertEquals(vendor.getName(), updated.getName());
        assertEquals(vendor.getIsActive(), updated.getIsActive());
    }

    @Test
    public void searchVendor_OK() throws ServiceException {
        BaseDTO dto = new BaseDTO();
        dto.setName("test1");
        dto.setPage(1);
        dto.setSize(10);

        SearchResult<Vendor> searchResult = vendorService.search(dto);
        assertEquals(11, searchResult.getTotal());
        assertEquals(2, searchResult.getTotalPage());
    }

}
