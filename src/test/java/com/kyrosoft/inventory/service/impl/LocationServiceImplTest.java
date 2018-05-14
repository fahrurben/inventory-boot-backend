package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Location;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.service.LocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class LocationServiceImplTest extends BaseServiceImplTest {

    @Autowired
    LocationService locationService;

    @Before
    public void before() {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
    }

    @Test
    public void searchLocation_OK() throws ServiceException {
        BaseDTO dto = new BaseDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Location> searchResult = locationService.search(dto);
        assertEquals(20, searchResult.getTotal());
    }
}
