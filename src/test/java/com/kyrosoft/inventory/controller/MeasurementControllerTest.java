package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Measurement;
import com.kyrosoft.inventory.model.dto.MeasurementDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        setupGeneric();
    }

    @Test
    public void createMeasurement_OK() throws Exception {

        Measurement measurement = new Measurement();
        measurement.setName(stringTest);
        measurement.setCode(stringTest);
        measurement.setUnitValue(doubleTest);
        measurement.setIsBasicUnit(boolTest);
        measurement.setIsActive(boolTest);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(post("/api/measurements"), measurement))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void updateMeasurement_OK() throws Exception {

        Measurement measurement = this.measurements.get(0);
        measurement.setName(stringUpdated);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                put("/api/measurements/" + measurement.getId()),
                                measurement
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(stringUpdated));
    }

    @Test
    public void getMeasurement_OK() throws Exception {
        Measurement measurement = this.measurements.get(0);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                get("/api/measurements/" + measurement.getId()),
                                measurement
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(measurement.getId()));
    }

    @Test
    public void searchMeasurement_OK() throws Exception {
        MeasurementDTO dto = new MeasurementDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                post("/api/measurements/search"),
                                dto
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(20)));
    }
}
