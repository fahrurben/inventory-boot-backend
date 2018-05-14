package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Measurement;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.MeasurementDTO;
import com.kyrosoft.inventory.service.MeasurementService;
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
public class MeasurementServiceImplTest extends BaseServiceImplTest {

    @Autowired
    MeasurementService measurementService;

    @Before
    public void before() {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
    }

    @Test
    public void searchMeasurement_OK() throws ServiceException {
        MeasurementDTO dto = new MeasurementDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Measurement> searchResult = measurementService.search(dto);
        assertEquals(20, searchResult.getTotal());
    }

    @Test
    public void searchMeasurement_Filter_Code_OK() throws ServiceException {
        MeasurementDTO dto = new MeasurementDTO();
        dto.setCode("test10");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Measurement> searchResult = measurementService.search(dto);
        assertEquals(1, searchResult.getTotal());
    }

    @Test
    public void searchMeasurement_Filter_IsBasicUnit_OK() throws ServiceException {
        measurements.get(0).setIsBasicUnit(false);
        measurementService.update(measurements.get(0));

        MeasurementDTO dto = new MeasurementDTO();
        dto.setIsBasicUnit(false);
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Measurement> searchResult = measurementService.search(dto);
        assertEquals(1, searchResult.getTotal());
    }

}
